package com.noonoo.application.service.mapper

import com.noonoo.application.port.`in`.command.MemberSignUpCommand
import com.noonoo.domain.model.Members
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface MemberMapper {

    fun fromMember(memberSignUpCommand: MemberSignUpCommand): Members

}
