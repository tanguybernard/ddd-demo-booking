package com.example.training.preparation.application.session

import com.example.training.preparation.domain.session.SessionId

class CreateTrainingSession(
    private val trainingSessionDomainService: TrainingSessionDomainService
) {


    fun execute(command: CreateSessionCommand): SessionId {
        return trainingSessionDomainService.createSessionFromTrainingCourse(command)

    }

}
