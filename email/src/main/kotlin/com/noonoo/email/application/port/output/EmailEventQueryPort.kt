package com.noonoo.email.application.port.output

import com.noonoo.email.domain.email.SignUpEmailEvent

interface EmailEventQueryPort {
    fun findByMemberId(memberId: Long): SignUpEmailEvent?
}
