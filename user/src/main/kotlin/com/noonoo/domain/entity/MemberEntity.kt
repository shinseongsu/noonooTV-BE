package com.noonoo.domain.entity

object MemberEntity : BaseTimeEntity("members") {
    val email = varchar("email", 255).uniqueIndex()
    val encryptPassword = varchar("encrypt_password", 255)
    val name = varchar("name", 255)
    val isVerified = bool("is_verified").default(false)
}
