package com.noonoo.batch.job.signup.step

import com.noonoo.batch.domain.vo.SignUpEmailSendMessage
import org.springframework.batch.integration.async.AsyncItemWriter
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.kafka.core.KafkaTemplate

@Configuration
class SignUpEmailSendWriterConfig(
    @Qualifier("taskExecutor") private val taskExecutor: TaskExecutor,
    private val kafkaTemplate: KafkaTemplate<String, SignUpEmailSendMessage>
) {
    fun asyncItemWriter(): AsyncItemWriter<SignUpEmailSendMessage> {
        val asyncItemWriter: AsyncItemWriter<SignUpEmailSendMessage> = AsyncItemWriter()
        asyncItemWriter.setDelegate(itemWriter())
        return asyncItemWriter
    }

    fun itemWriter(): ItemWriter<SignUpEmailSendMessage> =
        ItemWriter<SignUpEmailSendMessage> { items ->
            items.forEach {
                kafkaTemplate.send(TOPIC, it)
            }
        }

    companion object {
        const val TOPIC = "register-email-send"
    }
}
