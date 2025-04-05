package com.noonoo.user.domain.token

import kotlinx.datetime.Clock
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random
import kotlin.time.Duration

object EmailToken {
    val secret = "hello_noonoo"

    fun generate(
        email: String,
        validDuration: Duration,
    ): String {
        val expiresAt = Clock.System.now() + validDuration
        val randomPart = Random.Default.nextBytes(16).joinToString("") { "%02x".format(it) }
        val payload = "$randomPart.${expiresAt.toEpochMilliseconds()}"
        val signature = hmacSha256(payload, secret)
        val token = "$payload.$signature"

        return token
    }

    private fun hmacSha256(
        data: String,
        secret: String,
    ): String {
        val hmac = Mac.getInstance("HmacSHA256")
        val keySpec = SecretKeySpec(secret.toByteArray(Charsets.UTF_8), "HmacSHA256")
        hmac.init(keySpec)
        return hmac
            .doFinal(data.toByteArray(Charsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }
}
