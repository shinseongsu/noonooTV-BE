package com.noonoo.domain.repository

interface CrudRepository<DOMAIN: BaseModel> {

    fun save(domain: DOMAIN): DOMAIN

    fun findAll(): List<DOMAIN>

    fun findById(id: Long): DOMAIN?

}
