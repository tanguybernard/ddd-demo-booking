package com.example.training.preparation.application.session

import com.example.training.preparation.domain.TrainerId
import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.TrainingId
import com.example.training.preparation.domain.session.Session
import com.example.training.preparation.domain.session.SessionId
import com.example.training.preparation.domain.session.SessionRepository
import java.time.Period

class TrainingSessionDomainService(
    private val sessionRepository: SessionRepository,
    private val trainingCourseRepository: TrainingCourseRepository
) {



    fun createSessionFromTrainingCourse(command: CreateSessionCommand): SessionId {
        val training = trainingCourseRepository.getTrainingCourseBy(TrainingId(command.trainingId))

        if (training.duration != Period.between(command.startDate, command.endDate).days) {
            throw SessionPeriodIsIncorrectToTheDurationOfACourse
        }

        val sessionId = sessionRepository.nextId()
        sessionRepository.create(
            Session(
                sessionId, TrainingId(command.trainingId),
                TrainerId(command.trainerId),
                command.startDate,
                command.endDate
            )
        )

        return sessionId
    }



}
