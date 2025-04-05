package com.noonoo.user.domain.collection

data class SignUpEmailSendMessage(
    val memberId: Long,
    val name: String,
    val email: String,
    val token: String,
    val eventType: String,
    val attributes: String,
)
