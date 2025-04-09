package com.noonoo.user.adapter.output

import com.noonoo.user.adapter.output.persistence.MemberCustomRepository
import com.noonoo.user.adapter.output.persistence.MemberRepository
import com.noonoo.user.application.port.output.MemberCommandPort
import com.noonoo.user.application.port.output.MemberQueryPort
import com.noonoo.user.domain.model.MembersModel
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
    private val memberCustomRepository: MemberCustomRepository
) : MemberCommandPort,
    MemberQueryPort {
    override fun findById(id: Long): MembersModel =
        memberRepository.findById(id)
            ?: throw IllegalArgumentException("존재하지 않는 이메일 입니다.")

    override fun findByEmail(email: String): MembersModel? =
        memberCustomRepository.findByEmail(email)

    override fun save(members: MembersModel): MembersModel {
        if (this.findByEmail(members.email) != null) {
            throw IllegalArgumentException("이미 가입된 이메일 입니다.")
        }

        return memberRepository.save(members)
    }

    override fun update(members: MembersModel) {
        memberRepository.update(members)
    }
}
