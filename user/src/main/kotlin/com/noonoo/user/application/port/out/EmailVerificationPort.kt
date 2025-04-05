package com.noonoo.user.application.port.out

import com.noonoo.user.domain.model.EmailVerification

interface EmailVerificationPort {
    fun findByToken(token: String): EmailVerification

    fun save(emailVerification: EmailVerification): EmailVerification

    fun update(emailVerification: EmailVerification): EmailVerification
}
