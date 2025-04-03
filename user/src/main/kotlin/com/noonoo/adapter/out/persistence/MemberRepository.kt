package com.noonoo.adapter.out.persistence

import com.noonoo.domain.entity.MemberEntity
import com.noonoo.domain.model.Members
import com.noonoo.domain.repository.ExposedCrudRepository
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.springframework.stereotype.Repository

@Repository
class MemberRepository : ExposedCrudRepository<MemberEntity, Members> {

    override val table: MemberEntity = MemberEntity

    override fun toRow(domain: Members): MemberEntity.(InsertStatement<EntityID<Long>>) -> Unit = {
        if(domain.id != null) {
            it[id] = domain.id!!
        }
        it[name] = domain.name
        it[email] = domain.email
        it[encryptPassword] = domain.encryptPassword
        it[isVerified] = domain.isVerified
    }

    override fun toDomain(row: ResultRow): Members {
        return Members(
            id = row[MemberEntity.id].value,
            email = row[MemberEntity.email],
            encryptPassword = row[MemberEntity.encryptPassword],
            name = row[MemberEntity.name],
            isVerified = row[MemberEntity.isVerified]
        )
    }
}
