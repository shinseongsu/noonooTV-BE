package com.noonoo.user.domain.vo

import kotlinx.datetime.LocalDateTime

data class MemberShip(
    val memberId: Long,
    val nickName: String,
    val resolution: String,
    val screenCount: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isPaused: Boolean
)
