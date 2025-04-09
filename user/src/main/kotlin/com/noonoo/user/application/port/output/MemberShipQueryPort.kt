package com.noonoo.user.application.port.output

import com.noonoo.user.domain.vo.MemberShip

interface MemberShipQueryPort {
    fun findByMemberId(memberId: Long): MemberShip?
}
