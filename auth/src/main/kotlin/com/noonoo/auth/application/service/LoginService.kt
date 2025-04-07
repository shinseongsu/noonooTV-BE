package com.noonoo.auth.application.service

import com.noonoo.auth.application.port.input.LoginUseCase
import com.noonoo.auth.application.port.input.query.LoginQuery
import com.noonoo.auth.application.port.output.MemberClientPort
import com.noonoo.auth.application.port.output.SessionCommandPort
import com.noonoo.auth.domain.jwt.JwtProvider
import com.noonoo.auth.domain.vo.MemberLogin
import com.noonoo.auth.domain.vo.MemberToken
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val memberClientPort: MemberClientPort,
    private val sessionCommandPort: SessionCommandPort,
    private val jwtProvider: JwtProvider
) : LoginUseCase {
    override suspend fun login(loginQuery: LoginQuery): MemberToken {
        val members =
            memberClientPort.login(
                MemberLogin(
                    email = loginQuery.email,
                    password = loginQuery.password
                )
            )

        val refreshToken = jwtProvider.createRefreshToken()
        sessionCommandPort.save(refreshToken, members)

        return MemberToken(
            accessToken = jwtProvider.createAccessToken(members.email),
            refreshToken = refreshToken
        )
    }
}
