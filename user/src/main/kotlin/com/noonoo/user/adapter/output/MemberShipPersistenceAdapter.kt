package com.noonoo.user.adapter.output

import com.noonoo.user.adapter.output.persistence.MemberShipCustomRepository
import com.noonoo.user.application.port.output.MemberShipQueryPort
import com.noonoo.user.domain.vo.MemberShip
import org.springframework.stereotype.Component

@Component
class MemberShipPersistenceAdapter(
    private val memberShipCustomRepository: MemberShipCustomRepository
) : MemberShipQueryPort {
    override fun findByMemberId(memberId: Long): MemberShip? =
        memberShipCustomRepository.findByMemberId(memberId)
}
