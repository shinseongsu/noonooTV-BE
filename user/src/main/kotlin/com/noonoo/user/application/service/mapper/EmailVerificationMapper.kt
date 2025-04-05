package com.noonoo.user.application.service.mapper

import com.noonoo.user.domain.model.EmailVerification
import com.noonoo.user.domain.model.Members
import com.noonoo.user.domain.token.EmailToken
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.stereotype.Component
import kotlin.time.Duration

@Component
class EmailVerificationMapper {
    fun mapper(members: Members): EmailVerification {
        val tokenValidity = Duration.parse("PT30M")

        return EmailVerification(
            token = EmailToken.generate(members.email, tokenValidity),
            expiresAt =
                Clock.System
                    .now()
                    .plus(tokenValidity)
                    .toLocalDateTime(TimeZone.currentSystemDefault()),
            verifiedAt = null,
            memberId = members.id!!,
        )
    }
}
