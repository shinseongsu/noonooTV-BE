package com.noonoo.user.adapter.output.mongo

import com.noonoo.user.domain.collection.EmailSendEventCollection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailSendEventRepository : MongoRepository<EmailSendEventCollection, String>
