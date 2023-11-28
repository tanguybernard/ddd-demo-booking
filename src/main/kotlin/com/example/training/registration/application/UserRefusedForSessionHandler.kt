package com.example.training.registration.application

import com.example.training.registration.domain.session.SessionRepository
import com.example.training.registration.domain.session.events.UserRefusedForSessionEvent
import com.example.training.shared.DomainEventSubscriber

class UserRefusedForSessionHandler(
    private val emailSender: EmailSender,
    private val sessionRepository: SessionRepository): DomainEventSubscriber<UserRefusedForSessionEvent>
{
    override fun handleEvent(aDomainEvent: UserRefusedForSessionEvent) {

        val session = sessionRepository.getSessionBy(aDomainEvent.sessionId)

        emailSender.sendEmail(
            aDomainEvent.userEmail,
            "Training Session : ${session.title.value}",
            "Your training request has been rejected." // + comment
        )

    }
}