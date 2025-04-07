package com.noonoo.auth.adapter.output

import com.noonoo.auth.adapter.output.client.MemberClient
import com.noonoo.auth.adapter.output.client.dto.LoginClientRequest
import com.noonoo.auth.application.port.output.MemberClientPort
import com.noonoo.auth.domain.vo.MemberLogin
import com.noonoo.auth.domain.vo.Members
import org.springframework.stereotype.Component

@Component
class MemberClientAdapter(
    private val memberClient: MemberClient
) : MemberClientPort {
    override suspend fun login(memberLogin: MemberLogin): Members =
        memberClient
            .login(
                LoginClientRequest(
                    email = memberLogin.email,
                    password = memberLogin.password
                )
            ).also {
                if (it.code != "SUCCESS") {
                    throw IllegalArgumentException("Login failed: ${it.message}")
                }
            }.let {
                Members(
                    memberId = it.memberId!!,
                    email = it.email!!,
                    name = it.name!!,
                    nickName = it.nickName,
                    resolution = it.resolution,
                    screenCount = it.screenCount,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    isPaused = it.isPaused
                )
            }
}
