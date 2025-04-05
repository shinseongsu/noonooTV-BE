package com.noonoo.user.domain.entity

object SocialAccountEntity : BaseTimeEntity("social_accounts") {
    val providerUserId = varchar("provider_user_id", 255).uniqueIndex()
    val provider = varchar("provider", 20).nullable()
    val accessToken = varchar("access_token", 255).nullable()
    val refreshToken = varchar("refresh_token", 255).nullable()
    val member = reference("member_id", MemberEntity.id)
}
