package com.noonoo.user.application.port.output

import com.noonoo.user.domain.model.Members

interface MemberSignUpPort {
    fun save(members: Members): Members

    fun findById(id: Long): Members

    fun update(members: Members)
}
