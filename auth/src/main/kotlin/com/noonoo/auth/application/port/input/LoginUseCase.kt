package com.noonoo.auth.application.port.input

import com.noonoo.auth.application.port.input.query.LoginQuery
import com.noonoo.auth.domain.vo.MemberToken

interface LoginUseCase {
    suspend fun login(loginQuery: LoginQuery): MemberToken
}
