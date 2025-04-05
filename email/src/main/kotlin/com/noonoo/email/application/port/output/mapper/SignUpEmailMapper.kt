package com.noonoo.email.application.port.output.mapper

import com.noonoo.email.domain.email.SignUpEmail
import com.noonoo.email.domain.email.SignUpEmailEvent
import org.springframework.stereotype.Component

@Component
class SignUpEmailMapper {
    fun mapper(
        signUpEmailEvent: SignUpEmailEvent,
        name: String,
    ): SignUpEmail =
        SignUpEmail(
            id = signUpEmailEvent.id,
            memberId = signUpEmailEvent.memberId,
            token = signUpEmailEvent.token,
            name = name,
            email = signUpEmailEvent.email,
            eventType = signUpEmailEvent.eventType,
        )
}
