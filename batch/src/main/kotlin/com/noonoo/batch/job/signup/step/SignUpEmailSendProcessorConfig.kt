package com.noonoo.batch.job.signup.step

import com.noonoo.batch.domain.collection.EmailSendEventCollection
import com.noonoo.batch.domain.vo.SignUpEmailSendMessage
import org.springframework.batch.integration.async.AsyncItemProcessor
import org.springframework.batch.item.ItemProcessor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor

@Configuration
class SignUpEmailSendProcessorConfig(
    @Qualifier("taskExecutor") private val taskExecutor: TaskExecutor
) {
    @Bean
    fun asyncItemProcessor(): AsyncItemProcessor<EmailSendEventCollection, SignUpEmailSendMessage> {
        val asyncItemProcessor =
            AsyncItemProcessor<EmailSendEventCollection, SignUpEmailSendMessage>()
        asyncItemProcessor.setDelegate(signUpEmailSendProcessor())
        asyncItemProcessor.setTaskExecutor(taskExecutor)
        return asyncItemProcessor
    }

    fun signUpEmailSendProcessor()
        : ItemProcessor<EmailSendEventCollection, SignUpEmailSendMessage> =
            ItemProcessor { item ->
                SignUpEmailSendMessage(
                    memberId = item.memberId,
                    name = item.name,
                    email = item.email,
                    token = item.token,
                    eventType = item.eventType,
                    attributes = item.attributes
                )
            }
}
