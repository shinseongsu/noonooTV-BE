package com.noonoo.application.port.`in`.mapper

import com.noonoo.adapter.`in`.web.dto.MemberSignUpRequest
import com.noonoo.application.port.`in`.command.MemberSignUpCommand
import com.noonoo.global.common.encrypt.BcryptEncrypt
import org.springframework.stereotype.Component

@Component
class MemberSignCommandMapper(
    private val bcryptEncrypt: BcryptEncrypt,
) {

    fun mapper(memberSignUpRequest: MemberSignUpRequest) : MemberSignUpCommand {
        return MemberSignUpCommand(
            email = memberSignUpRequest.email!!,
            encryptPassword = bcryptEncrypt.encrypt(memberSignUpRequest.password!!),
            name = memberSignUpRequest.name!!,
        )
    }

}
