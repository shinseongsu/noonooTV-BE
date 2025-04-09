package com.noonoo.user.application.service

import com.noonoo.user.application.port.input.MemberLoginUseCase
import com.noonoo.user.application.port.input.command.MemberLoginQuery
import com.noonoo.user.application.port.output.MemberQueryPort
import com.noonoo.user.application.port.output.MemberShipQueryPort
import com.noonoo.user.domain.vo.Members
import org.springframework.stereotype.Service

@Service
class MemberLoginService(
    private val memberQueryPort: MemberQueryPort,
    private val memberShipQueryPort: MemberShipQueryPort
) : MemberLoginUseCase {
    override fun login(memberLoginQuery: MemberLoginQuery): Members {
        val members =
            memberQueryPort.findByEmail(memberLoginQuery.email)
                ?: throw IllegalArgumentException("존재하지 않는 이메일 입니다.")

        if (members.encryptedPassword != memberLoginQuery.encryptedPassword) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }
        val membership = memberShipQueryPort.findByMemberId(members.id!!)

        return Members(
            id = members.id!!,
            email = members.email,
            name = members.name,
            nickName = membership?.nickName,
            resolution = membership?.resolution,
            screenCount = membership?.screenCount,
            startDate = membership?.startDate,
            endDate = membership?.endDate,
            isPaused = membership?.isPaused
        )
    }
}
