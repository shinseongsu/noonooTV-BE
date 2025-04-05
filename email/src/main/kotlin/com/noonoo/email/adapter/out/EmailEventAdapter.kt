package com.noonoo.email.adapter.out

import com.noonoo.email.adapter.out.mongo.EmailEventRepository
import com.noonoo.email.adapter.out.smtp.SignUpEmailClient
import com.noonoo.email.application.port.out.EmailEventPort
import com.noonoo.email.domain.email.SignUpEmail
import com.noonoo.email.domain.email.SignUpEmailEvent
import org.springframework.stereotype.Component

@Component
class EmailEventAdapter(
    private val emailEventRepository: EmailEventRepository,
    private val signUpEmailClient: SignUpEmailClient,
) : EmailEventPort {
    override fun findByMemberId(memberId: Long): SignUpEmailEvent? = emailEventRepository.findByMemberId(memberId)

    override fun updateByEmail(email: String) {
        emailEventRepository.updateByEmail(email)
    }

    override fun sendSignEmail(signUpEmail: SignUpEmail) {
        signUpEmailClient.sendSignUpEmail(signUpEmail)
    }
}
