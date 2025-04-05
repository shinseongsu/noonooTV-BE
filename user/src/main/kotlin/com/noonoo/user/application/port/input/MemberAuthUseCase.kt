package com.noonoo.user.application.port.input

import com.noonoo.user.application.port.input.command.MemberSignUpCommand

interface MemberAuthUseCase {
    fun signUp(memberSignUpCommand: MemberSignUpCommand)

    fun verifyEmail(token: String)
}
