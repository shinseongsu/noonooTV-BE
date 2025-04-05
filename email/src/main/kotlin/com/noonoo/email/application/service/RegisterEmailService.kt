package com.noonoo.email.application.service

import com.noonoo.email.application.port.`in`.RegisterEmailUseCase
import com.noonoo.email.application.port.out.EmailEventPort
import com.noonoo.email.application.port.out.mapper.SignUpEmailMapper
import com.noonoo.user.domain.collection.SignUpEmailSendMessage
import org.springframework.stereotype.Service

@Service
class RegisterEmailService(
    private val emailEventPort: EmailEventPort,
    private val signUpEmailMapper: SignUpEmailMapper,
) : RegisterEmailUseCase {
    override fun signUpEmailSend(signUpEmailSendMessage: SignUpEmailSendMessage) {
        emailEventPort
            .findByMemberId(signUpEmailSendMessage.memberId)
            ?.let { signUpEmailMapper.mapper(it, signUpEmailSendMessage.name) }
            ?.run { emailEventPort.sendSignEmail(this) }
            .run { emailEventPort.updateByEmail(signUpEmailSendMessage.email) }
    }
}
