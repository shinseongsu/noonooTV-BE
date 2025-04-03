package com.noonoo.application.port.out

import com.noonoo.domain.model.Members

interface MemberSignUpPort {

    fun signUp(members: Members) : Members

}
