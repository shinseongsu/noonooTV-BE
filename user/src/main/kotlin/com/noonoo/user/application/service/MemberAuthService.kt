package com.noonoo.user.application.service

import com.noonoo.user.application.port.input.MemberSignUpUseCase
import com.noonoo.user.application.port.input.command.MemberSignUpCommand
import com.noonoo.user.application.port.output.EmailVerificationCommandPort
import com.noonoo.user.application.port.output.EmailVerificationQueryPort
import com.noonoo.user.application.port.output.MemberCommandPort
import com.noonoo.user.application.port.output.MemberQueryPort
import com.noonoo.user.application.service.mapper.EmailSendEventMapper
import com.noonoo.user.application.service.mapper.EmailVerificationMapper
import com.noonoo.user.application.service.mapper.MemberMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAuthService(
    private val memberCommandPort: MemberCommandPort,
    private val memberQueryPort: MemberQueryPort,
    private val emailVerificationCommandPort: EmailVerificationCommandPort,
    private val emailVerificationQueryPort: EmailVerificationQueryPort,
    private val memberMapper: MemberMapper,
    private val emailVerificationMapper: EmailVerificationMapper,
    private val emailSendEventMapper: EmailSendEventMapper,
    private val applicationEventPublisher: ApplicationEventPublisher
) : MemberSignUpUseCase {
    @Transactional
    override fun signUp(memberSignUpCommand: MemberSignUpCommand) {
        val members =
            memberMapper
                .mapper(memberSignUpCommand)
                .let { memberCommandPort.save(it) }

        val emailVerification =
            emailVerificationMapper
                .mapper(members)
                .let { emailVerificationCommandPort.save(it) }

        emailSendEventMapper
            .mapper(members, emailVerification.token)
            .run {
                applicationEventPublisher.publishEvent(this)
            }
    }

    @Transactional
    override fun verifyEmail(token: String) {
        val emailVerification = emailVerificationQueryPort.findByToken(token)
        val members = memberQueryPort.findById(emailVerification.memberId)

        if (emailVerification.verifiedAt != null) {
            throw IllegalArgumentException("이미 인증된 이메일 입니다.")
        }
        if (emailVerification.isExpired()) {
            throw IllegalArgumentException("인증 시간이 만료되었습니다.")
        }

        emailVerification.verify()
        members.verify()

        memberCommandPort.update(members)
        emailVerificationCommandPort.update(emailVerification)
    }
}
