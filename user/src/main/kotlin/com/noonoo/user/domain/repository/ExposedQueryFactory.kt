package com.noonoo.user.domain.repository

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class ExposedQueryFactory {
    fun fetch(query: () -> Query): ResultRow? =
        transaction {
            query().firstOrNull()
        }

    fun fetchAll(query: () -> Query): Iterable<ResultRow> =
        transaction {
            query().toList()
        }
}
