package com.noonoo.application.port.`in`.command

data class MemberSignUpCommand(
    val email: String,
    val encryptedPassword: String,
    val name: String,
)
