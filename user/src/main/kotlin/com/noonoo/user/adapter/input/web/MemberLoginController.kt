package com.noonoo.user.adapter.input.web

import com.noonoo.user.adapter.input.web.dto.MemberLoginRequest
import com.noonoo.user.adapter.input.web.dto.MemberLoginResponse
import com.noonoo.user.application.port.input.MemberLoginUseCase
import com.noonoo.user.application.port.input.mapper.MemberLoginMapper
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member/login")
class MemberLoginController(
    private val memberLoginUseCase: MemberLoginUseCase,
    private val memberLoginMapper: MemberLoginMapper
) {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun login(
        @Valid @RequestBody memberLoginRequest: MemberLoginRequest
    ): MemberLoginResponse {
        val members = memberLoginUseCase.login(memberLoginMapper.mapper(memberLoginRequest))

        return MemberLoginResponse(
            code = HttpStatus.OK.name,
            message = "로그인 성공",
            memberId = members.id,
            name = members.name,
            email = members.email,
            nickName = members.nickName,
            resolution = members.resolution,
            screenCount = members.screenCount,
            startDate = members.startDate?.toString(),
            endDate = members.endDate?.toString(),
            isPaused = members.isPaused
        )
    }
}
