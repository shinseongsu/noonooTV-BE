package com.noonoo.email.adapter.output

import com.noonoo.email.adapter.output.smtp.SignUpEmailClient
import com.noonoo.email.application.port.output.EmailSendPort
import com.noonoo.email.domain.email.SignUpEmail
import org.springframework.stereotype.Component

@Component
class EmailSendAdapter(
    private val signUpEmailClient: SignUpEmailClient
) : EmailSendPort {
    override fun sendSignEmail(signUpEmail: SignUpEmail) {
        signUpEmailClient.sendSignUpEmail(signUpEmail)
    }
}
