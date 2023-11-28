package com.example.training.registration.infrastructure.fake

import com.example.training.registration.application.EmailSender
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Fake EmailSender implementation
 */
@Service
class FakeEmailSender : EmailSender {
    private val logger = LoggerFactory.getLogger(EmailSender::class.java)

    override fun sendEmail(userEmail: String, title: String, body: String) {
        logger.info(">> Sending email: $userEmail - $title - $body")
    }
}