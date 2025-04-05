package com.noonoo.email.domain.email

data class SignUpEmail(
    val id: String,
    val memberId: Long,
    val token: String,
    val name: String,
    val email: String,
    val eventType: String,
)
