package com.noonoo.email.adapter.out.mongo

import com.noonoo.email.domain.email.SignUpEmailEvent
import com.noonoo.user.domain.collection.EmailSendEventCollection
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.updateMulti
import org.springframework.stereotype.Repository

@Repository
class EmailEventRepository(
    private val mongoTemplate: MongoTemplate,
) {
    fun findByMemberId(memberId: Long): SignUpEmailEvent? {
        val query =
            Query().apply {
                addCriteria(Criteria.where("memberId").`is`(memberId))
                addCriteria(Criteria.where("eventType").`is`("SIGN_UP"))
                addCriteria(Criteria.where("isSendMail").`is`(false))
            }

        return mongoTemplate
            .findOne(query, EmailSendEventCollection::class.java)
            ?.let {
                SignUpEmailEvent(
                    id = it.id!!,
                    memberId = it.memberId,
                    email = it.email,
                    eventType = it.eventType,
                    token = it.token,
                    attributes = it.attributes,
                )
            }
    }

    fun updateByEmail(email: String) {
        val query =
            Query().apply {
                addCriteria(Criteria.where("email").`is`(email))
                addCriteria(Criteria.where("eventType").`is`("SIGN_UP"))
            }
        val update =
            Update().apply {
                set("isSendMail", true)
            }

        mongoTemplate.updateMulti<EmailSendEventCollection>(query, update)
    }
}
