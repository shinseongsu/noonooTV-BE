package com.noonoo.user.adapter.output.persistence

import com.noonoo.user.domain.entity.EmailVerificationEntity
import com.noonoo.user.domain.model.EmailVerification
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class EmailVerificationCustomerRepository {
    fun findByToken(token: String): EmailVerification? =
        EmailVerificationEntity
            .selectAll()
            .where {
                EmailVerificationEntity.token eq token
            }.map {
                EmailVerification(
                    id = it[EmailVerificationEntity.id].value,
                    token = it[EmailVerificationEntity.token],
                    expiresAt = it[EmailVerificationEntity.expiresAt],
                    verifiedAt = it[EmailVerificationEntity.verifiedAt],
                    memberId = it[EmailVerificationEntity.memberId],
                )
            }.firstOrNull()
}
