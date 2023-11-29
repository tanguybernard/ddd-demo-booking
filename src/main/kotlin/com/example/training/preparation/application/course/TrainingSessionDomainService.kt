package com.example.training.preparation.application.course

import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.preparation.domain.course.*
import java.time.Period
import java.util.UUID

class TrainingSessionDomainService(
    private val trainingCourseRepository: TrainingCourseRepository
) {



    fun createSessionFromTrainingCourse(command: CreateSessionCommand): SessionId {
        val training = trainingCourseRepository.getTrainingCourseBy(TrainingId(command.trainingId))

        if (training.duration != Period.between(command.startDate, command.endDate).days) {
            throw SessionPeriodIsIncorrectToTheDurationOfACourse
        }

        val sessionId = SessionId(UUID.randomUUID().toString())
        training.addSession(
            Session(
                sessionId,
                TrainerId(command.trainerId),
                command.startDate,
                command.endDate
            )
        )
        trainingCourseRepository.save(training)

        return sessionId
    }



}
