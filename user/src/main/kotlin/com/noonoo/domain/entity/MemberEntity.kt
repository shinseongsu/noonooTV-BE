package com.noonoo.domain.entity

object MemberEntity : BaseTimeEntity("members") {
    val email = varchar("email", 255).uniqueIndex()
    val encryptedPassword = varchar("encrypted_password", 255)
    val name = varchar("name", 255)
    val isVerified = bool("is_verified").default(false)
}
