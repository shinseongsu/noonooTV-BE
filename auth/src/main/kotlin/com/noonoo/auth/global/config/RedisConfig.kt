package com.noonoo.auth.global.config

import com.noonoo.auth.domain.session.MemberSession
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, MemberSession> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer = Jackson2JsonRedisSerializer(MemberSession::class.java)

        val context =
            RedisSerializationContext
                .newSerializationContext<String, MemberSession>(keySerializer)
                .value(valueSerializer)
                .build()

        return ReactiveRedisTemplate(factory, context)
    }
}
