package com.noonoo.user.adapter.output.persistence

import com.noonoo.user.domain.entity.EmailVerificationEntity
import com.noonoo.user.domain.model.EmailVerification
import com.noonoo.user.domain.repository.ExposedCrudRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.springframework.stereotype.Repository

@Repository
class EmailVerificationRepository :
    ExposedCrudRepository<EmailVerificationEntity, EmailVerification> {
    override val table = EmailVerificationEntity

    override fun toRow(
        domain: EmailVerification
    ): EmailVerificationEntity.(InsertStatement<EntityID<Long>>) -> Unit =
        {
            if (domain.id != null) {
                it[id] = domain.id!!
            }
            it[token] = domain.token
            it[expiresAt] = domain.expiresAt
            it[verifiedAt] = domain.verifiedAt
            it[memberId] = domain.memberId
        }

    override fun toDomain(row: ResultRow): EmailVerification =
        EmailVerification(
            id = row[EmailVerificationEntity.id].value,
            token = row[EmailVerificationEntity.token],
            expiresAt = row[EmailVerificationEntity.expiresAt],
            verifiedAt = row[EmailVerificationEntity.verifiedAt],
            memberId = row[EmailVerificationEntity.memberId]
        )

    override fun updateRow(
        domain: EmailVerification
    ): EmailVerificationEntity.(UpdateStatement) -> Unit =
        {
            it[verifiedAt] = domain.verifiedAt
        }
}
