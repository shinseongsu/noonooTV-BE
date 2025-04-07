package com.noonoo.user.domain.entity

object MemberProfileEntity : BaseTimeEntity("member_profiles") {
    val nickname = varchar("nickname", 100).nullable()
    val profileUrl = varchar("profile_url", 500).nullable()
    val ageGroup = varchar("age_group", 20).nullable()
    val isMainProfile = bool("is_main_profile").default(false)
    val memberId = long("member_id")
}
