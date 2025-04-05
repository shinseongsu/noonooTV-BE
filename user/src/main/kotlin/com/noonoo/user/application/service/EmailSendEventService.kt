package com.noonoo.user.application.service

import com.noonoo.user.application.port.output.EmailEventPort
import com.noonoo.user.application.service.mapper.EmailSendEventMapper
import com.noonoo.user.domain.collection.EmailSendEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class EmailSendEventService(
    private val emailEventPort: EmailEventPort,
    private val emailSendEventMapper: EmailSendEventMapper
) {
    @Async
    @TransactionalEventListener
    fun handleEmailSendEvent(emailSendEvent: EmailSendEvent) {
        val emailSendEventCollection = emailSendEventMapper.mapper(emailSendEvent)
        val emailSendMessage = emailSendEventMapper.mapper(emailSendEventCollection)

        emailEventPort.save(emailSendEventCollection)
        emailEventPort.sendEmailEvent(emailSendMessage)
    }
}
