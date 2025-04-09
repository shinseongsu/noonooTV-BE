package com.noonoo.auth.adapter.output.client

import com.noonoo.auth.adapter.output.client.dto.LoginClientRequest
import com.noonoo.auth.adapter.output.client.dto.LoginClientResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class MemberClient(
    private val webClient: WebClient
) {
    suspend fun login(loginClientRequest: LoginClientRequest): LoginClientResponse =
        webClient
            .post()
            .bodyValue(loginClientRequest)
            .retrieve()
            .awaitBody()
}
