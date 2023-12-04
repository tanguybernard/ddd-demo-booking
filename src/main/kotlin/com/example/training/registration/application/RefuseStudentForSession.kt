package com.example.training.registration.application

import com.example.training.registration.domain.session.SessionId
import com.example.training.registration.domain.session.SessionRepository
import com.example.training.shared.DomainEventPublisher

class RefuseStudentForSession(private val sessionRepository: SessionRepository) {
    fun execute(email: String, sessionId: String) {

        val session = sessionRepository.getSessionBy(SessionId(sessionId))

        session.removeUserBy(email)

        sessionRepository.saveSession(session)


        //DomainEvent
        //Case where aggregate root subscribed for events raised by members of its aggregates (child entities)
        session.pullDomainEvents().stream().forEach(DomainEventPublisher.getInstance()::dispatch)

    }

}