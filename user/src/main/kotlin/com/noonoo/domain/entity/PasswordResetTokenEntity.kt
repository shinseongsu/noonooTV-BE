package com.noonoo.domain.entity

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object PasswordResetTokenEntity : BaseTimeEntity("password_reset_tokens") {
    val token = varchar("token", 255).uniqueIndex()
    val isUsed = bool("is_used").default(false)
    val expiresAt = datetime("expires_at")
    val member = reference("member_id", MemberEntity.id)
}
