package com.noonoo.auth.domain.session

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("member")
data class MemberSession(
    @Id
    val refreshToken: String,
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
