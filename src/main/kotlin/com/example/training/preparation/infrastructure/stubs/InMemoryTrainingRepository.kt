package com.example.training.preparation.infrastructure.stubs

import com.example.training.preparation.domain.course.TrainingCourse
import com.example.training.preparation.domain.course.CourseId
import com.example.training.preparation.domain.course.TrainingCourseRepository
import java.util.UUID

class InMemoryTrainingRepository : TrainingCourseRepository {

    private var map= HashMap<CourseId, TrainingCourse>()
    override fun getTrainingCourseBy(id: CourseId): TrainingCourse {
        return map[id] ?: throw TrainingCourseDoesNotExist()
    }

    override fun createTrainingCourse(trainingCourse: TrainingCourse) : TrainingCourse {
        map[trainingCourse.courseId] = trainingCourse
        return trainingCourse
    }

    override fun update(trainingCourse: TrainingCourse) {
        map[trainingCourse.courseId] = trainingCourse
    }

    override fun nextId(): CourseId {
        return CourseId(UUID.randomUUID().toString())
    }

    override fun save(course: TrainingCourse) {
        map[course.courseId] = course
    }


}