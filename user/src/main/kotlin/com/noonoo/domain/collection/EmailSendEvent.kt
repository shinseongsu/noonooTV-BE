package com.noonoo.domain.collection

data class EmailSendEvent(
    val memberId: Long,
    val name: String,
    val email: String,
    val eventType: String,
    val attributes: String,
)
