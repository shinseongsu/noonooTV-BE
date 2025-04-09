package com.noonoo.user.application.port.input

import com.noonoo.user.application.port.input.command.MemberLoginQuery
import com.noonoo.user.domain.vo.Members

interface MemberLoginUseCase {
    fun login(memberLoginQuery: MemberLoginQuery): Members
}
