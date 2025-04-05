package com.noonoo.user.adapter.out.kafka

import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class RegisterEmailSendProducer(
    private val kafkaTemplate: KafkaTemplate<String, SignUpEmailSendMessage>,
) {
    protected val log: KLogger = KotlinLogging.logger { this.javaClass.name }

    fun sendEmailEvent(emailSendMessage: SignUpEmailSendMessage) {
        kafkaTemplate
            .send(TOPIC, emailSendMessage)
            .toCompletableFuture()
            .thenAccept { result ->
                log.info { "✅ Kafka 전송 성공: ${result.recordMetadata}" }
            }.exceptionally { ex ->
                log.error { "❌ Kafka 전송 실패: ${ex.message}" }
                null
            }
    }

    companion object {
        const val TOPIC = "register-email-send"
    }
}
