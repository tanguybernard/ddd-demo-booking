package com.example.training.preparation.domain.trainer

import com.example.training.preparation.domain.course.SessionId
import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.CourseId

class UpdateTrainerOnSessionDomainService(
    val trainerRepository: TrainerRepository,
    val courseRepository: TrainingCourseRepository
) {

    //TODO replace by command
    fun execute(trainerId: String, courseId: String, sessionId: String) {

        val trainer = trainerRepository.getTrainerBy(TrainerId(trainerId))

        val course = courseRepository.getTrainingCourseBy(CourseId(courseId))

        course.updateTrainerForSession(SessionId(sessionId), trainer.trainerId)

        courseRepository.save(course)

    }
}