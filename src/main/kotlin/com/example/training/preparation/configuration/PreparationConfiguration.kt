package com.example.training.preparation.configuration

import com.example.training.preparation.application.course.SessionRemovedHandler
import com.example.training.preparation.domain.SessionRemovedDomainEvent
import com.example.training.preparation.domain.course.TrainingCourseRepository
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
class PreparationConfiguration {

    @Autowired
    lateinit var messagePublisher: MessagePublisher

    @Bean
    fun trainingRepository(): TrainingCourseRepository {
        return InMemoryTrainingRepository();
    }


    @Bean
    fun courseRemovedHandler(): Component {
        return SessionRemovedHandler(
            messagePublisher,
            trainingRepository(),
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