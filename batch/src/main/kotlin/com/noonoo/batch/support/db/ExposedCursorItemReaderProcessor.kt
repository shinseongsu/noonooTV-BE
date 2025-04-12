package com.noonoo.batch.support.db

import java.sql.ResultSet
import java.time.LocalDateTime
import javax.sql.DataSource
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader

class ExposedCursorItemReaderProcessor<T : Any>(
    name: String,
    dataSource: DataSource,
    private val fetchSize: Int = 1_000,
    private val clazz: KClass<T>,
    private val query: () -> Query
) : AbstractItemCountingItemStreamItemReader<T>() {
    private var connection: Database
    private lateinit var resultSet: ResultSet

    init {
        setName(name)
        connection = Database.connect(dataSource)
    }

    override fun doRead(): T? {
        return if (resultSet.next()) {
            val constructor = clazz.primaryConstructor ?: return null

            val metaData = resultSet.metaData
            val columnNames = (1..metaData.columnCount).map { metaData.getColumnName(it) }

            val params =
                constructor.parameters.associateWith { param ->
                    val property =
                        clazz.memberProperties.firstOrNull { it.name == param.name }
                            ?: return@associateWith null

                    val columnName =
                        property.getter.findAnnotation<ResultQuery>()?.name ?: param.name
                    val value =
                        if (columnName in
                            columnNames
                        ) {
                            resultSet.getObject(columnName)
                        } else {
                            null
                        }

                    if (value == null) {
                        null
                    } else {
                        when (param.type.classifier) {
                            Int::class -> (value as? Number)?.toInt()
                            Long::class -> (value as? Number)?.toLong()
                            Double::class -> (value as? Number)?.toDouble()
                            Float::class -> (value as? Number)?.toFloat()
                            Boolean::class -> value as? Boolean
                            String::class -> value as? String
                            LocalDateTime::class -> value as? LocalDateTime
                            else -> value
                        }
                    }
                }
            constructor.callBy(params)
        } else {
            null
        }
    }

    override fun doOpen() {
        val transaction = TransactionManager.manager.newTransaction()
        resultSet = query()
            .fetchSize(fetchSize)
            .execute(transaction) ?: throw IllegalStateException("Query is null")
    }

    override fun doClose() {
        resultSet.close()
    }
}
