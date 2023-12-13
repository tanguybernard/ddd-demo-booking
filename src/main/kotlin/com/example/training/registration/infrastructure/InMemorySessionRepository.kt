package com.example.training.registration.infrastructure

import com.example.training.registration.domain.session.SessionNotFound
import com.example.training.registration.domain.session.Session
import com.example.training.registration.domain.session.SessionId
import com.example.training.registration.domain.session.SessionRepository
import org.springframework.context.annotation.Scope

@Scope("singleton")
class InMemorySessionRepository : SessionRepository {

    private val sessions : HashMap<SessionId, Session> = hashMapOf()

    override fun getSessionBy(sessionId: SessionId): Session {
        return sessions[sessionId] ?: throw SessionNotFound()
    }

    override fun create(session: Session) {
        sessions[session.sessionId] = session
    }

    override fun saveSession(session: Session) {
        sessions[session.sessionId] = session
    }

}
