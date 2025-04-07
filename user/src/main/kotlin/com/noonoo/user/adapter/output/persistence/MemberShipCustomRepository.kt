package com.noonoo.user.adapter.output.persistence

import com.noonoo.user.domain.entity.MemberSubscriptionEntity
import com.noonoo.user.domain.entity.MembershipEntity
import com.noonoo.user.domain.repository.ExposedQueryFactory
import com.noonoo.user.domain.vo.MemberShip
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component

@Component
class MemberShipCustomRepository(
    private val exposedQueryFactory: ExposedQueryFactory
) {
    fun findByMemberId(memberId: Long): MemberShip? =
        exposedQueryFactory
            .fetch {
                MemberSubscriptionEntity
                    .join(
                        MembershipEntity,
                        joinType = JoinType.INNER,
                        additionalConstraint = {
                            MemberSubscriptionEntity.membership eq MembershipEntity.id
                        }
                    ).selectAll()
                    .where {
                        (MemberSubscriptionEntity.memberId eq memberId) and
                            (MemberSubscriptionEntity.isPaused eq false)
                    }
            }?.let {
                MemberShip(
                    memberId = it[MemberSubscriptionEntity.memberId],
                    nickName = it[MembershipEntity.nickName],
                    resolution = it[MembershipEntity.resolution],
                    screenCount = it[MembershipEntity.screenCount],
                    startDate = it[MemberSubscriptionEntity.startDate],
                    endDate = it[MemberSubscriptionEntity.endDate],
                    isPaused = it[MemberSubscriptionEntity.isPaused]
                )
            }
}
