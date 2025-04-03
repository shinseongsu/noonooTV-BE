package com.noonoo.global.common.encrypt

import at.favre.lib.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class BcryptEncrypt {

    fun encrypt(plainText: String) : String {
        return BCrypt.withDefaults().hashToString(12, plainText.toCharArray())
    }

}
