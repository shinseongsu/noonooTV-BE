package com.noonoo.user.domain.entity

import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MemberSubscriptionEntity : BaseTimeEntity("member_subscriptions") {
    val member = reference("member_id", MemberEntity.id)
    val membership = reference("membership_id", MembershipEntity.id)
    val startDate = datetime("start_date").defaultExpression(CurrentDateTime).nullable()
    val endDate = datetime("end_date").nullable()
    val isPaused = bool("is_paused").default(false)
}
