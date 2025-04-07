package com.noonoo.auth.domain.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtProvider(
    @Value("\${jwt.secret.key}") val secretKey: String
) {
    private val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
    private val expiration = 60 * 60 * 1000 // 1 hour

    fun createAccessToken(userId: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)
        return Jwts
            .builder()
            .subject(userId)
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(key)
            .compact()
    }

    fun createRefreshToken(): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration * 24)
        return Jwts
            .builder()
            .issuedAt(now)
            .expiration(expiryDate)
            .signWith(key)
            .compact()
    }
}
