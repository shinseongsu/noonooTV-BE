package com.noonoo.user.application.port.output

import com.noonoo.user.domain.model.EmailVerificationModel

interface EmailVerificationCommandPort {
    fun save(emailVerification: EmailVerificationModel): EmailVerificationModel

    fun update(emailVerification: EmailVerificationModel): EmailVerificationModel
}
