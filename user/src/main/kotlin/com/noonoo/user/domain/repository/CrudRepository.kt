package com.noonoo.user.domain.repository

interface CrudRepository<DOMAIN : BaseModel> {
    fun save(domain: DOMAIN): DOMAIN

    fun findAll(): List<DOMAIN>

    fun findById(id: Long): DOMAIN?

    fun update(domain: DOMAIN): DOMAIN
}
