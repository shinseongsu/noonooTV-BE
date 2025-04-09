package com.noonoo.user.application.port.input.mapper

import com.noonoo.user.adapter.input.web.dto.MemberLoginRequest
import com.noonoo.user.application.port.input.command.MemberLoginQuery
import com.noonoo.user.global.common.encrypt.BcryptEncrypt
import org.springframework.stereotype.Component

@Component
class MemberLoginMapper(
    private val bcryptEncrypt: BcryptEncrypt
) {
    fun mapper(memberLoginRequest: MemberLoginRequest): MemberLoginQuery =
        MemberLoginQuery(
            email = memberLoginRequest.email,
            encryptedPassword =
                bcryptEncrypt.encrypt(
                    memberLoginRequest.password
                )
        )
}
