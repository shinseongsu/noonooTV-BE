package com.noonoo.user.adapter.output

import com.noonoo.user.adapter.output.kafka.RegisterEmailSendProducer
import com.noonoo.user.adapter.output.mongo.EmailSendEventRepository
import com.noonoo.user.application.port.output.EmailSendEventCommandPort
import com.noonoo.user.domain.collection.EmailSendEventCollection
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import org.springframework.stereotype.Component

@Component
class EmailEventAdapter(
    private val emailEventRepository: EmailSendEventRepository,
    private val registerEmailSendProducer: RegisterEmailSendProducer
) : EmailSendEventCommandPort {
    override fun save(emailSendEventCollection: EmailSendEventCollection) {
        emailEventRepository.save(emailSendEventCollection)
    }

    override fun sendEmailEvent(signUpEmailSendMessage: SignUpEmailSendMessage) {
        registerEmailSendProducer.sendEmailEvent(signUpEmailSendMessage)
    }
}
