package com.noonoo.user.application.port.output

import com.noonoo.user.domain.model.EmailVerificationModel

interface EmailVerificationQueryPort {
    fun findByToken(token: String): EmailVerificationModel
}
