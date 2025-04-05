package com.noonoo.user.domain.model

import com.noonoo.user.domain.repository.BaseModel

data class Members(
    override var id: Long? = null,
    val email: String,
    val encryptedPassword: String,
    val name: String,
    var isVerified: Boolean = false,
) : BaseModel {
    fun verify() {
        this.isVerified = true
    }
}
