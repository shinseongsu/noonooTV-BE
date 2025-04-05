package com.noonoo.user.application.port.`in`

import com.noonoo.user.application.port.`in`.command.MemberSignUpCommand

interface MemberAuthUseCase {
    fun signUp(memberSignUpCommand: MemberSignUpCommand)

    fun verifyEmail(token: String)
}
