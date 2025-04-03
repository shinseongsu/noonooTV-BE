package com.noonoo.domain.model

import com.noonoo.domain.repository.BaseModel

data class Members(
    override var id: Long? = null,
    val email: String,
    val encryptedPassword: String,
    val name: String,
    val isVerified: Boolean = false,
) : BaseModel
