package com.example.training.preparation.domain

import com.example.training.preparation.domain.course.CourseId
import com.example.training.preparation.domain.course.SessionId
import com.example.training.shared.AggregateRoot
import com.example.training.shared.DomainEvent

class SessionRemovedDomainEvent(val courseId: CourseId, val  sessionId: SessionId) : DomainEvent {
    override fun getName(): String {
        return NAME
    }

    override fun getAggregateRootId(): String {
        return courseId.value
    }

    companion object {
        const val NAME = "COURSE_REMOVED"
    }
}

