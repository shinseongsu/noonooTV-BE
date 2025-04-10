package com.noonoo.email.adapter.output

import com.noonoo.email.adapter.output.mongo.EmailEventRepository
import com.noonoo.email.application.port.output.EmailEventCommandPort
import com.noonoo.email.application.port.output.EmailEventQueryPort
import com.noonoo.email.domain.email.SignUpEmailEvent
import org.springframework.stereotype.Component

@Component
class EmailEventAdapter(
    private val emailEventRepository: EmailEventRepository
) : EmailEventCommandPort,
    EmailEventQueryPort {
    override fun findByMemberId(memberId: Long): SignUpEmailEvent? = emailEventRepository.findByMemberId(memberId)

    override fun updateByEmail(email: String) {
        emailEventRepository.updateByEmail(email)
    }
}
