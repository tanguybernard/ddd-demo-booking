package com.example.training.preparation.domain.course

import com.example.training.preparation.domain.SessionRemovedDomainEvent
import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.shared.AggregateRoot

class TrainingCourse(val courseId: CourseId, val trainingName: TrainingName, val duration: Int)
    : AggregateRoot<CourseId>(courseId){

    private val sessions: HashMap<SessionId, Session> = hashMapOf()


    fun removeSession(sessionId: SessionId) {
        sessions.remove(sessionId)
        this.record(SessionRemovedDomainEvent(courseId, sessionId))
    }

    fun getSessionBy(sessionId: SessionId) : Session {
        return sessions.getOrElse(sessionId) { throw SessionNotFound() }

    }

    fun addSession(session: Session) {
        sessions[session.sessionId] = session
    }

    fun updateTrainerForSession(sessionId: SessionId, trainerId: TrainerId) {
        sessions.getOrElse(sessionId) { throw SessionNotFound() }.trainerId = trainerId
    }

}
