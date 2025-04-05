package com.noonoo.user.application.service

import com.noonoo.user.application.port.`in`.MemberAuthUseCase
import com.noonoo.user.application.port.`in`.command.MemberSignUpCommand
import com.noonoo.user.application.port.out.EmailVerificationPort
import com.noonoo.user.application.port.out.MemberSignUpPort
import com.noonoo.user.application.service.mapper.EmailSendEventMapper
import com.noonoo.user.application.service.mapper.EmailVerificationMapper
import com.noonoo.user.application.service.mapper.MemberMapper
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAuthService(
    private val memberSignUpPort: MemberSignUpPort,
    private val emailVerificationPort: EmailVerificationPort,
    private val memberMapper: MemberMapper,
    private val emailVerificationMapper: EmailVerificationMapper,
    private val emailSendEventMapper: EmailSendEventMapper,
    private val applicationEventPublisher: ApplicationEventPublisher,
) : MemberAuthUseCase {
    @Transactional
    override fun signUp(memberSignUpCommand: MemberSignUpCommand) {
        val members =
            memberMapper
                .mapper(memberSignUpCommand)
                .let { memberSignUpPort.save(it) }

        val emailVerification =
            emailVerificationMapper
                .mapper(members)
                .let { emailVerificationPort.save(it) }

        emailSendEventMapper
            .mapper(members, emailVerification.token)
            .run {
                applicationEventPublisher.publishEvent(this)
            }
    }

    @Transactional
    override fun verifyEmail(token: String) {
        val emailVerification = emailVerificationPort.findByToken(token)
        val members = memberSignUpPort.findById(emailVerification.memberId)

        if (emailVerification.verifiedAt != null) {
            throw IllegalArgumentException("이미 인증된 이메일 입니다.")
        }
        if (emailVerification.isExpired()) {
            throw IllegalArgumentException("인증 시간이 만료되었습니다.")
        }

        emailVerification.verify()
        members.verify()

        memberSignUpPort.update(members)
        emailVerificationPort.update(emailVerification)
    }
}
