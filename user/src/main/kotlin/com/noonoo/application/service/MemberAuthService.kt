package com.noonoo.application.service

import com.noonoo.adapter.out.MemberPersistenceAdapter
import com.noonoo.application.event.mapper.EmailSendEventMapper
import com.noonoo.application.port.`in`.MemberAuthUseCase
import com.noonoo.application.port.`in`.command.MemberSignUpCommand
import com.noonoo.application.service.mapper.MemberMapper
import com.noonoo.domain.model.Members
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAuthService(
    private val memberPersistenceAdapter: MemberPersistenceAdapter,
    private val memberMapper: MemberMapper,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val emailSendEventMapper: EmailSendEventMapper,
) : MemberAuthUseCase {

    @Transactional
    override fun signUp(memberSignUpCommand: MemberSignUpCommand) : Members {
        return memberMapper.fromMember(memberSignUpCommand)
            .let { memberPersistenceAdapter.signUp(it) }
            .also { applicationEventPublisher.publishEvent(emailSendEventMapper.mapper(it)) }
    }
}
