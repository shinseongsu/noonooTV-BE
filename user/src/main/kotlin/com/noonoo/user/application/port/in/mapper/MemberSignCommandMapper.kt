package com.noonoo.user.application.port.`in`.mapper

import com.noonoo.user.adapter.`in`.web.dto.MemberSignUpRequest
import com.noonoo.user.application.port.`in`.command.MemberSignUpCommand
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
