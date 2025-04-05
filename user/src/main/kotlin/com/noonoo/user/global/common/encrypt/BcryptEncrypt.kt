package com.noonoo.user.global.common.encrypt

import at.favre.lib.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class BcryptEncrypt {
    fun encrypt(plainText: String): String = BCrypt.withDefaults().hashToString(12, plainText.toCharArray())
}
