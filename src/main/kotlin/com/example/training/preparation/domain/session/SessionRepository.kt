package com.example.training.preparation.domain.session

interface SessionRepository {

    fun create(session: Session)
    fun nextId(): SessionId
    fun getSessionBy(sessionId: SessionId): Session

}