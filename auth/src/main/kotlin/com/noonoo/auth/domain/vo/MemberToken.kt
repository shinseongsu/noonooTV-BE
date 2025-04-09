package com.noonoo.auth.domain.vo

data class MemberToken(
    val accessToken: String,
    val refreshToken: String
)
