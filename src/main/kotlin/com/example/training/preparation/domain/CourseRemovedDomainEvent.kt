package com.example.training.preparation.domain

import com.example.training.shared.DomainEvent

class CourseRemovedDomainEvent : DomainEvent {
    override fun getName(): String {
        return NAME
    }

    companion object {
        const val NAME = "COURSE_REMOVED"
    }
}

