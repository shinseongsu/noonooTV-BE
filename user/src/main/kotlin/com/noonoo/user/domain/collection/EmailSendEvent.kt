package com.noonoo.user.domain.collection

data class EmailSendEvent(
    val memberId: Long,
    val name: String,
    val email: String,
    val token: String,
    val eventType: String,
    val attributes: String
)
