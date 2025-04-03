package com.noonoo.domain.collection

import kotlinx.datetime.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "email_send_event")
class EmailSendEventCollection(
    memberId: Long,
    name: String,
    email: String,
    eventType: String,
    attributes: String,
    isSendMail: Boolean,
    createdAt: LocalDateTime,
) {

    @Id
    var id: String? = null
        protected set

    var memberId: Long = memberId
        protected set

    var name: String = name
        protected set

    var email: String = email
        protected set

    var eventType: String = eventType
        protected set

    var attributes: String = attributes
        protected set

    var isSendMail: Boolean = isSendMail
        protected set

    var createdAt: LocalDateTime = createdAt
        protected set

}
