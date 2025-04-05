package com.noonoo.user.adapter.out

import com.noonoo.user.adapter.out.kafka.RegisterEmailSendProducer
import com.noonoo.user.adapter.out.mongo.EmailSendEventRepository
import com.noonoo.user.application.port.out.EmailEventPort
import com.noonoo.user.domain.collection.EmailSendEventCollection
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import org.springframework.stereotype.Component

@Component
class EmailEventAdapter(
    private val emailEventRepository: EmailSendEventRepository,
    private val registerEmailSendProducer: RegisterEmailSendProducer,
) : EmailEventPort {
    override fun save(emailSendEventCollection: EmailSendEventCollection) {
        emailEventRepository.save(emailSendEventCollection)
    }

    override fun sendEmailEvent(signUpEmailSendMessage: SignUpEmailSendMessage) {
        registerEmailSendProducer.sendEmailEvent(signUpEmailSendMessage)
    }
}
