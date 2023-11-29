package com.example.training.preparation.domain.course

import com.example.training.preparation.domain.SessionRemovedDomainEvent
import com.example.training.shared.AggregateRoot

class TrainingCourse(val trainingId: TrainingId, val trainingName: TrainingName, val duration: Int)
    : AggregateRoot<TrainingId>(trainingId){

    private val sessions: HashMap<SessionId, Session> = hashMapOf()


    fun removeSession(sessionId: SessionId) {
        sessions.remove(sessionId)
        this.record(SessionRemovedDomainEvent(trainingId, sessionId))
    }

    fun getSessionBy(sessionId: SessionId) : Session {
        return sessions.getOrElse(sessionId) { throw SessionNotFound() }

    }

    fun addSession(session: Session) {
        sessions[session.sessionId] = session
    }

}
