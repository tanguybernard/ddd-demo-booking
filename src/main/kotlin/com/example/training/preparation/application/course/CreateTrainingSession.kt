package com.example.training.preparation.application.course

import com.example.training.preparation.domain.course.SessionId

class CreateTrainingSession(
    private val trainingSessionDomainService: TrainingSessionDomainService
) {


    fun execute(command: CreateSessionCommand): SessionId {
        return trainingSessionDomainService.createSessionFromTrainingCourse(command)

    }

}
