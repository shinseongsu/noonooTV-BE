package com.noonoo.user.application.service.mapper

import com.noonoo.user.application.port.input.command.MemberSignUpCommand
import com.noonoo.user.domain.model.MembersModel
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface MemberMapper {
    fun mapper(memberSignUpCommand: MemberSignUpCommand): MembersModel
}
