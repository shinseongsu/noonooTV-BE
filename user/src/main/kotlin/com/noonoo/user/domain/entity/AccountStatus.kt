package com.noonoo.user.domain.entity

enum class AccountStatus(
    private val description: String
) {
    ACTIVE(
        description = "정상"
    ),
    INACTIVE(
        description = "비활성"
    ),
    SUSPENDED(
        description = "정지"
    )
}
