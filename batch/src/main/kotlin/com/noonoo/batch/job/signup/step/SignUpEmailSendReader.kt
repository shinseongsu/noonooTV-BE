package com.noonoo.batch.job.signup.step

import com.noonoo.batch.domain.collection.EmailSendEventCollection
import com.noonoo.batch.support.mongodb.MongoQueryItemFactory.mongoReactiveCursorItemReader
import com.noonoo.batch.support.mongodb.MongoQueryItemReader
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where

@Configuration
class SignUpEmailSendReader(
    private val mongoTemplate: ReactiveMongoTemplate
) {
    fun signUpEmailSendReader(): MongoQueryItemReader<EmailSendEventCollection> =
        mongoReactiveCursorItemReader(
            mongoTemplate = mongoTemplate
        ) {
            where("eventType")
                .`is`("SIGN_UP")
                .and("isSendMail")
                .`is`(false)
        }
}
