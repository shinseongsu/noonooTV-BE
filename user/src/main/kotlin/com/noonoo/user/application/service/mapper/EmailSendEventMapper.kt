package com.noonoo.user.application.service.mapper

import com.noonoo.user.domain.collection.EmailSendEvent
import com.noonoo.user.domain.collection.EmailSendEventCollection
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import com.noonoo.user.domain.model.MembersModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.stereotype.Component

@Component
class EmailSendEventMapper {
    fun mapper(
        members: MembersModel,
        token: String
    ): EmailSendEvent =
        EmailSendEvent(
            memberId = members.id!!,
            email = members.email,
            name = members.name,
            token = token,
            eventType = "SIGN_UP",
            attributes = "회원가입 축하드립니다."
        )

    fun mapper(emailSendEvent: EmailSendEvent): EmailSendEventCollection =
        EmailSendEventCollection(
            memberId = emailSendEvent.memberId,
            name = emailSendEvent.name,
            email = emailSendEvent.email,
            token = emailSendEvent.token,
            eventType = emailSendEvent.eventType,
            attributes = emailSendEvent.attributes,
            isSendMail = false,
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )

    fun mapper(emailSendEventCollection: EmailSendEventCollection): SignUpEmailSendMessage =
        SignUpEmailSendMessage(
            memberId = emailSendEventCollection.memberId,
            name = emailSendEventCollection.name,
            token = emailSendEventCollection.token,
            email = emailSendEventCollection.email,
            eventType = emailSendEventCollection.eventType,
            attributes = emailSendEventCollection.attributes
        )
}
