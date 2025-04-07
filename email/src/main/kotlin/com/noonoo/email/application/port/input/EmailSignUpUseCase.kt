package com.noonoo.email.application.port.input

import com.noonoo.user.domain.collection.SignUpEmailSendMessage

interface EmailSignUpUseCase {
    fun signUpEmailSend(signUpEmailSendMessage: SignUpEmailSendMessage)
}
