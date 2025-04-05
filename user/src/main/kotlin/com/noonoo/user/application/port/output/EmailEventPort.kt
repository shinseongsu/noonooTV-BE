package com.noonoo.user.application.port.output

import com.noonoo.user.domain.collection.EmailSendEventCollection
import com.noonoo.user.domain.collection.SignUpEmailSendMessage

interface EmailEventPort {
    fun save(emailSendEventCollection: EmailSendEventCollection)

    fun sendEmailEvent(signUpEmailSendMessage: SignUpEmailSendMessage)
}
