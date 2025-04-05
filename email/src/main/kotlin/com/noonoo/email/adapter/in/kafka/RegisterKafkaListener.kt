package com.noonoo.email.adapter.`in`.kafka

import com.noonoo.email.application.port.`in`.RegisterEmailUseCase
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class RegisterKafkaListener(
    private val registerEmailUseCase: RegisterEmailUseCase,
) {
    @KafkaListener(
        topics = [TOPIC],
        groupId = GROUP_ID,
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun signUpListen(
        @Payload message: SignUpEmailSendMessage,
    ) {
        registerEmailUseCase.signUpEmailSend(message)
    }

    companion object {
        const val TOPIC = "register-email-send"
        const val GROUP_ID = "email-service"
    }
}
