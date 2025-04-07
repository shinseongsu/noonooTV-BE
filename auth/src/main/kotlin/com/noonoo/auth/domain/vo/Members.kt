package com.noonoo.auth.domain.vo

data class Members(
    val memberId: Long,
    val email: String,
    val name: String,
    val nickName: String?,
    val resolution: String?,
    val screenCount: Int?,
    val startDate: String?,
    val endDate: String?,
    val isPaused: Boolean?
)
