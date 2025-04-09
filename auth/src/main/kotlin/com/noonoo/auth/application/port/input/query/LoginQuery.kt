package com.noonoo.auth.application.port.input.query

data class LoginQuery(
    val email: String,
    val password: String
)
