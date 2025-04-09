package com.noonoo.email.application.service

import com.noonoo.email.application.port.input.EmailSignUpUseCase
import com.noonoo.email.application.port.output.EmailEventCommandPort
import com.noonoo.email.application.port.output.EmailEventQueryPort
import com.noonoo.email.application.port.output.EmailSendPort
import com.noonoo.email.application.port.output.mapper.SignUpEmailMapper
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import org.springframework.stereotype.Service

@Service
class EmailSignUpService(
    private val emailEventCommandPort: EmailEventCommandPort,
    private val emailEventQueryPort: EmailEventQueryPort,
    private val emailSendPort: EmailSendPort,
    private val signUpEmailMapper: SignUpEmailMapper
) : EmailSignUpUseCase {
    override fun signUpEmailSend(signUpEmailSendMessage: SignUpEmailSendMessage) {
        val member = emailEventQueryPort.findByMemberId(signUpEmailSendMessage.memberId)
        val mappedEmail = member?.let { signUpEmailMapper.mapper(it, signUpEmailSendMessage.name) }
        val emailSent = mappedEmail?.let { emailSendPort.sendSignEmail(it) }
        if (emailSent != null) {
            emailEventCommandPort.updateByEmail(signUpEmailSendMessage.email)
        }
    }
}
