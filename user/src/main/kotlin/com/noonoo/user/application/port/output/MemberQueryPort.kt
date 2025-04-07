package com.noonoo.user.application.port.output

import com.noonoo.user.domain.model.MembersModel

interface MemberQueryPort {
    fun findById(id: Long): MembersModel

    fun findByEmail(email: String): MembersModel?
}
