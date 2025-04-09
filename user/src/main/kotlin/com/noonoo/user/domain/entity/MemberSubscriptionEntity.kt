package com.noonoo.user.domain.entity

import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object MemberSubscriptionEntity : BaseTimeEntity("member_subscriptions") {
    val memberId = long("member_id")
    val membership = long("membership_id")
    val startDate = datetime("start_date").defaultExpression(CurrentDateTime)
    val endDate = datetime("end_date")
    val isPaused = bool("is_paused").default(false)
}
