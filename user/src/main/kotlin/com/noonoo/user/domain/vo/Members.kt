package com.noonoo.user.domain.vo

import kotlinx.datetime.LocalDateTime

data class Members(
    val id: Long,
    val email: String,
    val name: String,
    val nickName: String?,
    val resolution: String?,
    val screenCount: Int?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val isPaused: Boolean?
)
