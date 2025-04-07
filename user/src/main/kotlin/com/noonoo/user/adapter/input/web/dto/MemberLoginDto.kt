package com.noonoo.user.adapter.input.web.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class MemberLoginRequest(
    val email: String,
    val password: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MemberLoginResponse(
    val code: String,
    val message: String,
    val memberId: Long,
    val name: String,
    val email: String,
    val nickName: String?,
    val resolution: String?,
    val screenCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val isPaused: Boolean?
)
