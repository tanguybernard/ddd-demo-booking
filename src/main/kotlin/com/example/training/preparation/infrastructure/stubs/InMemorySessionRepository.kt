package com.example.training.preparation.infrastructure.stubs

import com.example.training.preparation.domain.session.Session
import com.example.training.preparation.domain.session.SessionId
import com.example.training.preparation.domain.session.SessionNotFound
import com.example.training.preparation.domain.session.SessionRepository
import java.util.UUID


class InMemorySessionRepository: SessionRepository {

    val sessions = HashMap<SessionId, Session>()


    override fun create(session: Session) {
        sessions[session.sessionId] = session
    }

    override fun nextId(): SessionId {
        return SessionId(UUID.randomUUID().toString())
    }

    override fun getSessionBy(sessionId: SessionId): Session {
        return sessions[sessionId] ?: throw SessionNotFound()
    }

}
