package com.example.training.preparation.application.course

import com.example.training.preparation.domain.trainer.UpdateTrainerOnSessionDomainService

class UpdateTrainerOnSession(val domainService: UpdateTrainerOnSessionDomainService) {


    fun execute(trainerId: String, trainingId: String, sessionId: String) {

        domainService.execute(trainerId, trainingId, sessionId)

        //TODO domain event to alert the replaced trainer
        // if session is published, raise integration event on BC registration to change trainer
    }
}