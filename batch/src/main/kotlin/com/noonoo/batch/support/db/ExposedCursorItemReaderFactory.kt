package com.noonoo.batch.support.db

import javax.sql.DataSource
import org.jetbrains.exposed.sql.Query

class ExposedCursorItemReaderFactory {
    inline fun <reified T : Any> ExposedCursorItemReader(
        name: String,
        dataSource: DataSource,
        fetchSize: Int = 1_000,
        noinline query: () -> Query
    ): ExposedCursorItemReaderProcessor<T> =
        ExposedCursorItemReaderProcessor(
            name = name,
            dataSource = dataSource,
            fetchSize = fetchSize,
            clazz = T::class,
            query = query
        )
}
