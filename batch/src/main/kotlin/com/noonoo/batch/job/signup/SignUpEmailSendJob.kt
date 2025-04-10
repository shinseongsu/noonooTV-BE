package com.noonoo.batch.job.signup

import com.noonoo.batch.domain.collection.EmailSendEventCollection
import com.noonoo.batch.domain.vo.SignUpEmailSendMessage
import com.noonoo.batch.job.signup.step.SignUpEmailSendProcessorConfig
import com.noonoo.batch.job.signup.step.SignUpEmailSendReader
import com.noonoo.batch.job.signup.step.SignUpEmailSendWriterConfig
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
class SignUpEmailSendJob(
    private val transactionManager: PlatformTransactionManager,
    private val signUpEmailSendReader: SignUpEmailSendReader,
    private val signUpEmailSendProcessorConfig: SignUpEmailSendProcessorConfig,
    private val signUpEmailSendWriterConfig: SignUpEmailSendWriterConfig
) {
    @Bean
    fun signUpEmailSend(jobRepository: JobRepository): Job =
        JobBuilder("signUpEmailSendJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(signUpEmailSendStep(jobRepository))
            .build()

    @Bean
    fun signUpEmailSendStep(jobRepository: JobRepository): Step =
        StepBuilder("signUpEmailSendStep", jobRepository)
            .chunk<EmailSendEventCollection, SignUpEmailSendMessage>(1_000, transactionManager)
            .reader(signUpEmailSendReader.signUpEmailSendReader())
            .processor(signUpEmailSendProcessorConfig.signUpEmailSendProcessor())
            .writer(signUpEmailSendWriterConfig.itemWriter())
            .build()
}
