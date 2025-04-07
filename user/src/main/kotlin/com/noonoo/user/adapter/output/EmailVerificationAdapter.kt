package com.noonoo.user.adapter.output

import com.noonoo.user.adapter.output.persistence.EmailVerificationCustomerRepository
import com.noonoo.user.adapter.output.persistence.EmailVerificationRepository
import com.noonoo.user.application.port.output.EmailVerificationCommandPort
import com.noonoo.user.application.port.output.EmailVerificationQueryPort
import com.noonoo.user.domain.model.EmailVerificationModel
import org.springframework.stereotype.Component

@Component
class EmailVerificationAdapter(
    private val emailVerificationCustomerRepository: EmailVerificationCustomerRepository,
    private val emailVerificationRepository: EmailVerificationRepository
) : EmailVerificationCommandPort,
    EmailVerificationQueryPort {
    override fun findByToken(token: String): EmailVerificationModel =
        emailVerificationCustomerRepository.findByToken(token)
            ?: throw IllegalArgumentException("인증 토큰이 존재하지 않습니다.")

    override fun save(emailVerification: EmailVerificationModel): EmailVerificationModel =
        emailVerificationRepository.save(emailVerification)

    override fun update(emailVerification: EmailVerificationModel): EmailVerificationModel =
        emailVerificationRepository.update(emailVerification)
}
