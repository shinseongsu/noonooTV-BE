package com.noonoo.user.application.port.output

import com.noonoo.user.domain.model.MembersModel

interface MemberCommandPort {
    fun save(members: MembersModel): MembersModel

    fun update(members: MembersModel)
}
