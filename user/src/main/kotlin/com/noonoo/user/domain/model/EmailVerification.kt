package com.noonoo.user.domain.model

import com.noonoo.user.domain.repository.BaseModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class EmailVerification(
    val token: String,
    val expiresAt: LocalDateTime,
    val memberId: Long,
    var verifiedAt: LocalDateTime?,
    override var id: Long? = null,
) : BaseModel {
    fun isExpired(): Boolean {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return currentDate > expiresAt
    }

    fun verify() {
        verifiedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }
}
