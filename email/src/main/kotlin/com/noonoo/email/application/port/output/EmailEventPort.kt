package com.noonoo.email.application.port.output

import com.noonoo.email.domain.email.SignUpEmail
import com.noonoo.email.domain.email.SignUpEmailEvent

interface EmailEventPort {
    fun findByMemberId(memberId: Long): SignUpEmailEvent?

    fun updateByEmail(email: String)

    fun sendSignEmail(signUpEmail: SignUpEmail)
}
