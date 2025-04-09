package com.noonoo.auth.application.port.output

import com.noonoo.auth.domain.vo.Members

interface SessionCommandPort {
    suspend fun save(
        refreshToken: String,
        members: Members
    )
}
