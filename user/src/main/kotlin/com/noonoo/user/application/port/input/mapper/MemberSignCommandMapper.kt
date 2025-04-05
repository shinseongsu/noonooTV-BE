package com.noonoo.user.application.port.input.mapper

import com.noonoo.user.adapter.input.web.dto.MemberSignUpRequest
import com.noonoo.user.application.port.input.command.MemberSignUpCommand
import com.noonoo.user.global.common.encrypt.BcryptEncrypt
import org.springframework.stereotype.Component

@Component
class MemberSignCommandMapper(
    private val bcryptEncrypt: BcryptEncrypt,
) {
    fun mapper(memberSignUpRequest: MemberSignUpRequest): MemberSignUpCommand =
        MemberSignUpCommand(
            email = memberSignUpRequest.email!!,
            encryptedPassword = bcryptEncrypt.encrypt(memberSignUpRequest.password!!),
            name = memberSignUpRequest.name!!,
        )
}
