package com.noonoo.user.adapter.output.persistence

import com.noonoo.user.domain.entity.MemberEntity
import com.noonoo.user.domain.model.Members
import com.noonoo.user.domain.repository.ExposedCrudRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.springframework.stereotype.Repository

@Repository
class MemberRepository : ExposedCrudRepository<MemberEntity, Members> {
    override val table: MemberEntity = MemberEntity

    override fun toRow(domain: Members): MemberEntity.(InsertStatement<EntityID<Long>>) -> Unit =
        {
            if (domain.id != null) {
                it[id] = domain.id!!
            }
            it[name] = domain.name
            it[email] = domain.email
            it[encryptedPassword] = domain.encryptedPassword
            it[isVerified] = domain.isVerified
        }

    override fun toDomain(row: ResultRow): Members =
        Members(
            id = row[MemberEntity.id].value,
            email = row[MemberEntity.email],
            encryptedPassword = row[MemberEntity.encryptedPassword],
            name = row[MemberEntity.name],
            isVerified = row[MemberEntity.isVerified],
        )

    override fun updateRow(domain: Members): MemberEntity.(UpdateStatement) -> Unit =
        {
            it[isVerified] = domain.isVerified
        }
}
