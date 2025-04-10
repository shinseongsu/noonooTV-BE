package com.noonoo.batch.support.mongodb

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.batch.item.ItemReader
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class MongoQueryItemReader<T>(
    private val mongoTemplate: ReactiveMongoTemplate,
    private val entityClass: Class<T>,
    private val query: Criteria.() -> Unit
) : ItemReader<T> {
    private var iterator: Iterator<T>? = null

    override fun read(): T? {
        if (iterator == null) {
            runBlocking {
                val criteria = Criteria().apply(query)
                val query = Query(criteria)
                val list =
                    mongoTemplate
                        .find(query, entityClass)
                        .collectList()
                        .awaitSingle()

                iterator = list.iterator()
            }
        }

        return if (iterator?.hasNext() == true) {
            iterator?.next()
        } else {
            null
        }
    }
}
