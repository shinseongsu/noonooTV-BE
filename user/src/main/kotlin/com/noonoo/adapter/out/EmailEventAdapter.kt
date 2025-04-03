package com.noonoo.adapter.out

import com.noonoo.adapter.out.kafka.EmailSendEventProducer
import com.noonoo.adapter.out.mongo.EmailSendEventRepository
import com.noonoo.application.port.out.EmailEventPort
import com.noonoo.domain.collection.EmailSendEvent
import com.noonoo.domain.collection.EmailSendEventCollection
import org.springframework.stereotype.Component

@Component
class EmailEventAdapter(
    private val emailEventRepository: EmailSendEventRepository,
    private val emailSendEventProducer: EmailSendEventProducer,
): EmailEventPort {

    override fun save(emailSendEventCollection: EmailSendEventCollection) {
        emailEventRepository.save(emailSendEventCollection)
    }

    override fun sendEmailEvent(emailSendEvent: EmailSendEvent) {
        emailSendEventProducer.sendEmailEvent(emailSendEvent)
    }
}
