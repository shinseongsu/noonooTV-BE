package com.noonoo.auth.adapter.output.redis

import com.noonoo.auth.domain.session.MemberSession
import java.time.Duration
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class MemberSessionRepository(
    private val redisTemplate: ReactiveRedisTemplate<String, MemberSession>
) {
    suspend fun save(session: MemberSession): Mono<Boolean> =
        redisTemplate
            .opsForValue()
            .set(session.refreshToken, session, Duration.ofDays(7))
}
