package com.noonoo.user.adapter.output

import com.noonoo.user.adapter.output.persistence.EmailVerificationCustomerRepository
import com.noonoo.user.adapter.output.persistence.EmailVerificationRepository
import com.noonoo.user.application.port.output.EmailVerificationPort
import com.noonoo.user.domain.model.EmailVerification
import org.springframework.stereotype.Component

@Component
class EmailVerificationAdapter(
    private val emailVerificationCustomerRepository: EmailVerificationCustomerRepository,
    private val emailVerificationRepository: EmailVerificationRepository,
) : EmailVerificationPort {
    override fun findByToken(token: String): EmailVerification =
        emailVerificationCustomerRepository.findByToken(token)
            ?: throw IllegalArgumentException("인증 토큰이 존재하지 않습니다.")

    override fun save(emailVerification: EmailVerification): EmailVerification = emailVerificationRepository.save(emailVerification)

    override fun update(emailVerification: EmailVerification): EmailVerification = emailVerificationRepository.update(emailVerification)
}
