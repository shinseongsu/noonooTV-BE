package com.noonoo.domain.entity

object MembershipEntity: BaseTimeEntity("memberships") {
    val nickName = varchar("nick_name", 50)
    val price = integer("price")
    val resolution = varchar("resolution", 50)
    val screenCount = integer("screen_count")
}
