package com.noonoo.auth.application.port.output

import com.noonoo.auth.domain.vo.MemberLogin
import com.noonoo.auth.domain.vo.Members

interface MemberClientPort {
    suspend fun login(memberLogin: MemberLogin): Members
}
