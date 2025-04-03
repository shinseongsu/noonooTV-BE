package com.noonoo.adapter.out.kafka

import com.noonoo.domain.collection.EmailSendEvent
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class EmailSendEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, EmailSendEvent>
) {
    protected val log: KLogger = KotlinLogging.logger { this.javaClass.name }

    fun sendEmailEvent(emailSendEvent: EmailSendEvent) {
        kafkaTemplate.send(TOPIC, emailSendEvent)
            .toCompletableFuture()
            .thenAccept { result ->
                log.info { "✅ Kafka 전송 성공: ${result.recordMetadata}" }
            }.exceptionally { ex ->
                log.error { "❌ Kafka 전송 실패: ${ex.message}" }
                null
            }

    }

    companion object {
        const val TOPIC = "email-send-event"
    }

}
