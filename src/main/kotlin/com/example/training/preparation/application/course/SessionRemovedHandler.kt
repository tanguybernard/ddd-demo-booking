package com.example.training.preparation.application.course

import com.example.training.preparation.domain.SessionRemovedDomainEvent
import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.preparation.domain.trainer.TrainerRepository
import com.example.training.shared.DomainEvent
import com.example.training.shared.infrastructure.MessagePublisher

class SessionRemovedHandler(
    private val bus: MessagePublisher?,
    private val trainingCourseRepository: TrainingCourseRepository,
    private val trainerRepository: TrainerRepository
) : Component {

    override fun send(event: DomainEvent) {
    }

    override fun receive(event: DomainEvent) {

        if(event is SessionRemovedDomainEvent) { //TODO can be improved
            /*val integrationEvent = SessionCourseIntegrationEvent()
            integrationEvent.name = "SESSION REMOVED"
            integrationEvent.id = event.sessionId.value*/
            this.bus?.publish("SESSION REMOVED")
        }





    }


}