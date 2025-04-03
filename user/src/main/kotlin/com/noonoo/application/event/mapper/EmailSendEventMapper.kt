package com.noonoo.application.event.mapper

import com.noonoo.domain.collection.EmailSendEvent
import com.noonoo.domain.collection.EmailSendEventCollection
import com.noonoo.domain.model.Members
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.stereotype.Component

@Component
class EmailSendEventMapper {

    fun mapper(members: Members) : EmailSendEvent {
        return EmailSendEvent(
            memberId = members.id!!,
            email = members.email,
            name = members.name,
            eventType = "SIGN_UP",
            attributes = "회원가입 축하드립니다.",
        )
    }

    fun mapper(emailSendEvent: EmailSendEvent) : EmailSendEventCollection {
        return EmailSendEventCollection(
            memberId = emailSendEvent.memberId,
            name = emailSendEvent.name,
            email = emailSendEvent.email,
            eventType = emailSendEvent.eventType,
            attributes = emailSendEvent.attributes,
            isSendMail = false,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        )
    }

}
