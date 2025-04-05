package com.noonoo.email.domain.email

data class SignUpEmailEvent(
    val id: String,
    val memberId: Long,
    val token: String,
    val email: String,
    val eventType: String,
    val attributes: String
)
