package com.noonoo.adapter.out.mongo

import com.noonoo.domain.collection.EmailSendEventCollection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailSendEventRepository : MongoRepository<EmailSendEventCollection, String>
