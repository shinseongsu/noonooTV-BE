package com.noonoo.auth.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${member.url}") private val url: String
) {
    @Bean
    fun webClient(): WebClient =
        WebClient
            .builder()
            .baseUrl(url)
            .build()
}
