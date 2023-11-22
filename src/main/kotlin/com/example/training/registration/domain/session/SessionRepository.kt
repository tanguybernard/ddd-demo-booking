package com.example.training.registration.domain.session

interface SessionRepository {

    fun getSessionBy(sessionId: SessionId): Session
    fun create(session: Session)

}
