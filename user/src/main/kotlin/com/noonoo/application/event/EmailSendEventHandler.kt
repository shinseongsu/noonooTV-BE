package com.noonoo.application.event

import com.noonoo.application.event.mapper.EmailSendEventMapper
import com.noonoo.application.port.out.EmailEventPort
import com.noonoo.domain.collection.EmailSendEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class EmailSendEventHandler(
    private val emailEventPort: EmailEventPort,
    private val emailSendEventMapper: EmailSendEventMapper,
) {

    @Async
    @TransactionalEventListener
    fun handleEmailSendEvent(event: EmailSendEvent) {
        emailSendEventMapper.mapper(event)
            .run { emailEventPort.save(this) }
            .run { emailEventPort.sendEmailEvent(event) }
    }

}
