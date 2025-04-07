package com.noonoo.email.application.port.output

interface EmailEventCommandPort {
    fun updateByEmail(email: String)
}
