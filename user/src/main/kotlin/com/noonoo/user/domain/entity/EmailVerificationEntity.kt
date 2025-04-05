package com.noonoo.user.domain.entity

import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object EmailVerificationEntity : BaseTimeEntity("email_verification") {
    val token = varchar("token", 255).uniqueIndex()
    val expiresAt = datetime("expires_at")
    val verifiedAt = datetime("verified_at").nullable()
    val memberId = long("member_id")
}
