package com.noonoo.user.application.service.mapper

import com.noonoo.user.domain.model.EmailVerificationModel
import com.noonoo.user.domain.model.MembersModel
import com.noonoo.user.domain.token.EmailToken
import kotlin.time.Duration
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.stereotype.Component

@Component
class EmailVerificationMapper {
    fun mapper(members: MembersModel): EmailVerificationModel {
        val tokenValidity = Duration.parse("PT30M")

        return EmailVerificationModel(
            token = EmailToken.generate(members.email, tokenValidity),
            expiresAt =
                Clock.System
                    .now()
                    .plus(tokenValidity)
                    .toLocalDateTime(TimeZone.currentSystemDefault()),
            verifiedAt = null,
            memberId = members.id!!
        )
    }
}
