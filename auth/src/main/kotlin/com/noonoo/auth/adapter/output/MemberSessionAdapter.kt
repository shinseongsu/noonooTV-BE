package com.noonoo.auth.adapter.output

import com.noonoo.auth.adapter.output.redis.MemberSessionRepository
import com.noonoo.auth.application.port.output.SessionCommandPort
import com.noonoo.auth.domain.session.MemberSession
import com.noonoo.auth.domain.vo.Members
import org.springframework.stereotype.Component

@Component
class MemberSessionAdapter(
    private val memberSessionRepository: MemberSessionRepository
) : SessionCommandPort {
    override suspend fun save(
        refreshToken: String,
        members: Members
    ) {
        memberSessionRepository.save(
            MemberSession(
                refreshToken = refreshToken,
                memberId = members.memberId,
                email = members.email,
                name = members.name,
                nickName = members.nickName,
                resolution = members.resolution,
                screenCount = members.screenCount,
                startDate = members.startDate,
                endDate = members.endDate,
                isPaused = members.isPaused
            )
        )
    }
}
