package com.example.training.registration

import com.example.training.registration.domain.session.Session
import com.example.training.registration.domain.session.SessionId
import com.example.training.registration.domain.session.SessionRepository

class InMemorySessionRepository : SessionRepository {

    private val sessions : HashMap<SessionId, Session> = hashMapOf()

    override fun getSessionBy(sessionId: SessionId): Session {
        return sessions[sessionId] ?: throw SessionNotFound()
    }

    override fun create(session: Session) {
        sessions[session.sessionId] = session
    }

}
