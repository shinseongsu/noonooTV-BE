package com.noonoo.user.adapter.output

import com.noonoo.user.adapter.output.persistence.MemberCustomRepository
import com.noonoo.user.adapter.output.persistence.MemberRepository
import com.noonoo.user.application.port.output.MemberSignUpPort
import com.noonoo.user.domain.model.Members
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val memberCustomRepository: MemberCustomRepository
) : MemberSignUpPort {
    override fun save(members: Members): Members {
        if (existsByEmail(members.email)) {
            throw IllegalArgumentException("이미 가입된 이메일 입니다.")
        }

        return memberRepository.save(members)
    }

    private fun existsByEmail(email: String): Boolean =
        memberCustomRepository.findByEmail(email) != null

    override fun findById(id: Long): Members =
        memberRepository.findById(id)
            ?: throw IllegalArgumentException("존재하지 않는 이메일 입니다.")

    override fun update(members: Members) {
        memberRepository.update(members)
    }
}
