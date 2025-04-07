package com.noonoo.auth.adapter.output.client.dto

data class LoginClientRequest(
    val email: String,
    val password: String
)

data class LoginClientResponse(
    val code: String,
    val message: String,
    val memberId: Long?,
    val name: String?,
    val email: String?,
    val nickName: String?,
    val resolution: String?,
    val screenCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val isPaused: Boolean?
)
