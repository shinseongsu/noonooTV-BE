package com.noonoo.email.application.service

import com.noonoo.email.application.port.input.RegisterEmailUseCase
import com.noonoo.email.application.port.output.EmailEventPort
import com.noonoo.email.application.port.output.mapper.SignUpEmailMapper
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import org.springframework.stereotype.Service

@Service
class RegisterEmailService(
    private val emailEventPort: EmailEventPort,
    private val signUpEmailMapper: SignUpEmailMapper
) : RegisterEmailUseCase {
    override fun signUpEmailSend(signUpEmailSendMessage: SignUpEmailSendMessage) {
        val member = emailEventPort.findByMemberId(signUpEmailSendMessage.memberId)
        val mappedEmail = member?.let { signUpEmailMapper.mapper(it, signUpEmailSendMessage.name) }
        val emailSent = mappedEmail?.let { emailEventPort.sendSignEmail(it) }
        if (emailSent != null) {
            emailEventPort.updateByEmail(signUpEmailSendMessage.email)
        }
    }
}
