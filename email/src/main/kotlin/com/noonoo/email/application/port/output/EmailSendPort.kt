package com.noonoo.email.application.port.output

import com.noonoo.email.domain.email.SignUpEmail

interface EmailSendPort {
    fun sendSignEmail(signUpEmail: SignUpEmail)
}
