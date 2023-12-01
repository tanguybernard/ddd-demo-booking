package com.example.training.preparation.application.course

import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.preparation.domain.course.*
import com.example.training.preparation.domain.trainer.TrainerCannotLeadThisTraining
import com.example.training.preparation.domain.trainer.TrainerRepository
import java.time.Period
import java.util.UUID

class TrainingSessionDomainService(
    private val trainerRepository: TrainerRepository,
    private val trainingCourseRepository: TrainingCourseRepository
) {



    fun createSessionFromTrainingCourse(command: CreateSessionCommand): SessionId {
        val training = trainingCourseRepository.getTrainingCourseBy(CourseId(command.trainingId))

        if (training.duration != Period.between(command.startDate, command.endDate).days) {
            throw SessionPeriodIsIncorrectToTheDurationOfACourse
        }

        //Can throw an exception, TrainerNotFound
        val trainer = trainerRepository.getTrainerBy(TrainerId(command.trainerId))

        if(!trainer.getExpertises().contains(training.courseId)) {
            throw TrainerCannotLeadThisTraining()
        }

        val sessionId = SessionId(UUID.randomUUID().toString())
        training.addSession(
            Session(
                sessionId,
                trainer.trainerId,
                command.startDate,
                command.endDate
            )
        )
        trainingCourseRepository.save(training)

        return sessionId
    }



}
