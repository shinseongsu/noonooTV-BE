package com.noonoo.domain.repository

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.transactions.transaction

interface ExposedCrudRepository<TABLE: LongIdTable, DOMAIN: BaseModel>: CrudRepository<DOMAIN> {
    val table: TABLE

    override fun save(domain: DOMAIN): DOMAIN = dbQuery {
        table.insertAndGetId(toRow(domain)).let { generatedId ->
            domain.apply { id = generatedId.value }
        }
    }

    override fun findAll(): List<DOMAIN> = dbQuery {
        table.selectAll().map { toDomain(it) }
    }

    override fun findById(id: Long): DOMAIN? = dbQuery {
        table.selectAll().where { table.id eq id }
            .map { toDomain(it) }
            .singleOrNull()
    }

    fun toRow(domain: DOMAIN): TABLE.(InsertStatement<EntityID<Long>>) -> Unit
    fun toDomain(row: ResultRow): DOMAIN

    private fun <T> dbQuery(block: () -> T): T = transaction {
        addLogger(StdOutSqlLogger)
        block()
    }
}
