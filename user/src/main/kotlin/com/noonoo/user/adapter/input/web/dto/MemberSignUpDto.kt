package com.noonoo.user.adapter.input.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class MemberSignUpRequest(
    @field:NotBlank(message = "{field.not_blank}")
    @field:Email(message = "{field.email}")
    val email: String?,
    @field:NotBlank(message = "{field.not_blank}")
    @field:Size(min = 8, max = 30, message = "{field.size}")
    val password: String?,
    @field:NotBlank(message = "{field.not_blank}")
    @field:Size(min = 2, max = 255, message = "{field.size}")
    val name: String?,
)

data class MemberSignUpResponse(
    val code: String,
    val message: String,
)
