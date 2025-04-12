package com.noonoo.user.adapter.output.persistence

import com.noonoo.user.domain.entity.MemberEntity
import com.noonoo.user.domain.model.MembersModel
import com.noonoo.user.domain.repository.ExposedCrudRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.springframework.stereotype.Repository

@Repository
class MemberRepository : ExposedCrudRepository<MemberEntity, MembersModel> {
    override val table: MemberEntity = MemberEntity

    override fun toRow(domain: MembersModel): MemberEntity.(InsertStatement<EntityID<Long>>) -> Unit =
        {
            if (domain.id != null) {
                it[id] = domain.id!!
            }
            it[name] = domain.name
            it[email] = domain.email
            it[encryptedPassword] = domain.encryptedPassword
            it[isVerified] = domain.isVerified
        }

    override fun toDomain(row: ResultRow): MembersModel =
        MembersModel(
            id = row[MemberEntity.id].value,
            email = row[MemberEntity.email],
            encryptedPassword = row[MemberEntity.encryptedPassword],
            name = row[MemberEntity.name],
            isVerified = row[MemberEntity.isVerified]
        )

    override fun updateRow(domain: MembersModel): MemberEntity.(UpdateStatement) -> Unit =
        {
            it[isVerified] = domain.isVerified
        }
}
