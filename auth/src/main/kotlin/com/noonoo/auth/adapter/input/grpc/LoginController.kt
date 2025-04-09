package com.noonoo.auth.adapter.input.grpc

import com.noonoo.auth.application.port.input.LoginUseCase
import com.noonoo.auth.application.port.input.query.LoginQuery
import com.noonoo.auth.domain.message.LoginGrpcKt
import com.noonoo.auth.domain.message.LoginRequest
import com.noonoo.auth.domain.message.LoginResponse
import org.springframework.stereotype.Component

@Component
class LoginController(
    private val loginUseCase: LoginUseCase
) : LoginGrpcKt.LoginCoroutineImplBase() {
    override suspend fun login(request: LoginRequest): LoginResponse {
        val login =
            loginUseCase.login(
                LoginQuery(
                    email = request.email ?: throw IllegalArgumentException("Email is required"),
                    password =
                        request.password
                            ?: throw IllegalArgumentException("Password is required")
                )
            )

        return LoginResponse
            .newBuilder()
            .setCode("SUCCESS")
            .setMessage("로그인 성공")
            .setAccessToken(login.accessToken)
            .setRefreshToken(login.refreshToken)
            .build()
    }
}
