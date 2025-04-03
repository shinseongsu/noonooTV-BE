package com.noonoo.application.port.`in`

import com.noonoo.application.port.`in`.command.MemberSignUpCommand
import com.noonoo.domain.model.Members

interface MemberAuthUseCase {
    fun signUp(memberSignUpCommand: MemberSignUpCommand) : Members
}
