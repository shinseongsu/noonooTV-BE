package com.noonoo.email.application.port.`in`

import com.noonoo.user.domain.collection.SignUpEmailSendMessage

interface RegisterEmailUseCase {
    fun signUpEmailSend(signUpEmailSendMessage: SignUpEmailSendMessage)
}
