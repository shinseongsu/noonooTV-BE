package com.noonoo.user.adapter.out.persistence

import com.noonoo.user.domain.entity.MemberEntity
import com.noonoo.user.domain.model.Members
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository

@Repository
class MemberCustomRepository {
    fun findByEmail(email: String): Members? =
        MemberEntity
            .selectAll()
            .where {
                MemberEntity.email eq email
            }.map {
                Members(
                    id = it[MemberEntity.id].value,
                    email = it[MemberEntity.email],
                    encryptedPassword = it[MemberEntity.encryptedPassword],
                    name = it[MemberEntity.name],
                    isVerified = it[MemberEntity.isVerified],
                )
            }.firstOrNull()
}
