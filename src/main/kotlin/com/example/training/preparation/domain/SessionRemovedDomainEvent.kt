package com.example.training.preparation.domain

import com.example.training.preparation.domain.course.TrainingId
import com.example.training.preparation.domain.course.SessionId
import com.example.training.shared.DomainEvent

class SessionRemovedDomainEvent(courseId: TrainingId, sessionId: SessionId) : DomainEvent {
    override fun getName(): String {
        return NAME
    }

    companion object {
        const val NAME = "COURSE_REMOVED"
    }
}

