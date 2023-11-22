package com.example.training.preparation.infrastructure.stubs

import com.example.training.preparation.domain.course.TrainingCourse
import com.example.training.preparation.domain.course.TrainingId
import com.example.training.preparation.domain.course.TrainingCourseRepository
import java.util.UUID

class InMemoryTrainingRepository : TrainingCourseRepository {

    private var map= HashMap<TrainingId, TrainingCourse>()
    override fun getTrainingCourseBy(id: TrainingId): TrainingCourse {
        return map[id] ?: throw TrainingCourseDoesNotExist()
    }

    override fun createTrainingCourse(trainingCourse: TrainingCourse) : TrainingCourse {
        map[trainingCourse.trainingId] = trainingCourse
        return trainingCourse
    }

    override fun update(trainingCourse: TrainingCourse) {
        map[trainingCourse.trainingId] = trainingCourse
    }

    override fun nextId(): TrainingId {
        return TrainingId(UUID.randomUUID().toString())
    }


}