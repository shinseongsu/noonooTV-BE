package com.noonoo.user.application.port.input.command

data class MemberLoginQuery(
    val email: String,
    val encryptedPassword: String
)
