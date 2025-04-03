package com.noonoo.adapter.out

import com.noonoo.adapter.out.persistence.MemberCustomRepository
import com.noonoo.adapter.out.persistence.MemberRepository
import com.noonoo.application.port.out.MemberSignUpPort
import com.noonoo.domain.model.Members
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val memberCustomRepository: MemberCustomRepository
) : MemberSignUpPort {

    override fun signUp(members: Members) : Members {
        if(existsByEmail(members.email)) {
            throw IllegalArgumentException("이미 가입된 이메일 입니다.")
        }

        return memberRepository.save(members)
    }

    private fun existsByEmail(email: String): Boolean {
        return memberCustomRepository.findByEmail(email) != null
    }
}
