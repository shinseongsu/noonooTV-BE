package com.noonoo.application.port.`in`.command

data class MemberSignUpCommand(
    val email: String,
    val encryptPassword: String,
    val name: String,
)
