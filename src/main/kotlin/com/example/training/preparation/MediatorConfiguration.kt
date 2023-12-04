package com.example.training.preparation

import com.example.training.preparation.application.course.SessionRemovedHandler
import com.example.training.preparation.domain.SessionRemovedDomainEvent
import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.preparation.domain.mediatorPattern.CourseImplMediator
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainerRepository
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainingRepository
import com.example.training.shared.infrastructure.MessagePublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MediatorConfiguration {

    @Autowired
    lateinit var redisPublisher: MessagePublisher


    @Bean
    fun courseRemovedHandler(): Component {
        return SessionRemovedHandler(
            redisPublisher,
            InMemoryTrainingRepository(),
            InMemoryTrainerRepository()
        )
    }



    @Bean
    fun courseMediator(): CourseMediator{
        val mediator = CourseImplMediator()
        mediator.addUser(SessionRemovedDomainEvent.NAME,courseRemovedHandler())
        return mediator

    }
}