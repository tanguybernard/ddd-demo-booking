package com.example.training.preparation.domain.course

import com.example.training.preparation.domain.CourseRemovedDomainEvent
import com.example.training.shared.AggregateRoot

class TrainingCourse(val trainingId: TrainingId, val trainingName: TrainingName, val duration: Int)
    : AggregateRoot<TrainingId>(trainingId){


    fun remove() {
        this.record(CourseRemovedDomainEvent())
    }

}
