package com.noonoo.user.application.port.input.command

data class MemberSignUpCommand(
    val email: String,
    val encryptedPassword: String,
    val name: String,
)
