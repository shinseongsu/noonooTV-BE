package com.noonoo.user.domain.entity

import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object LoginLogEntity : BaseTimeEntity("login_logs") {
    val member = reference("member_id", MemberEntity.id)
    val deviceName = varchar("device_name", 100)
    val os = varchar("os", 50)
    val country = varchar("country", 100)
    val ipAddress = varchar("ip_address", 50)
    val loggedInAt = datetime("logged_in_at").defaultExpression(CurrentDateTime)
}
