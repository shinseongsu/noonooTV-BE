package com.noonoo.user.adapter.input.web

import com.noonoo.user.adapter.input.web.dto.MemberSignUpRequest
import com.noonoo.user.adapter.input.web.dto.MemberSignUpResponse
import com.noonoo.user.adapter.input.web.dto.MemberVerifyResponse
import com.noonoo.user.application.port.input.MemberAuthUseCase
import com.noonoo.user.application.port.input.mapper.MemberSignCommandMapper
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class MemberAuthController(
    private val memberAuthUseCase: MemberAuthUseCase,
    private val memberSignUpCommandMapper: MemberSignCommandMapper,
) {
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(
        @Valid @RequestBody memberSignUpRequest: MemberSignUpRequest,
    ): MemberSignUpResponse {
        memberSignUpCommandMapper
            .mapper(memberSignUpRequest)
            .let(memberAuthUseCase::signUp)

        return MemberSignUpResponse(
            code = "SUCCESS",
            message = "회원가입 성공 되었습니다.",
        )
    }

    @GetMapping("/signup/verify/{token}")
    @ResponseStatus(HttpStatus.OK)
    fun verifyEmail(
        @PathVariable token: String,
    ): MemberVerifyResponse {
        memberAuthUseCase.verifyEmail(token)

        return MemberVerifyResponse(
            code = "SUCCESS",
            message = "이메일 인증 성공 되었습니다.",
        )
    }
}
