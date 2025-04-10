package com.noonoo.batch.support.mongodb

import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria

object MongoQueryItemFactory {
    inline fun <reified T> MongoReactiveCursorItemReader(
        mongoTemplate: ReactiveMongoTemplate,
        noinline query: Criteria.() -> Unit
    ): MongoQueryItemReader<T> =
        MongoQueryItemReader(
            mongoTemplate = mongoTemplate,
            entityClass = T::class.java,
            query = query
        )
}
