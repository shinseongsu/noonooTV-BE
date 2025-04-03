package com.noonoo.application.port.out

import com.noonoo.domain.collection.EmailSendEvent
import com.noonoo.domain.collection.EmailSendEventCollection

interface EmailEventPort {

    fun save(emailSendEventCollection: EmailSendEventCollection)

    fun sendEmailEvent(emailSendEvent: EmailSendEvent)

}
