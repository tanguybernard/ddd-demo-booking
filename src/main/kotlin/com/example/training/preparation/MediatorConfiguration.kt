package com.example.training.preparation

import com.example.training.preparation.application.course.CourseRemovedHandler
import com.example.training.preparation.domain.CourseRemovedDomainEvent
import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.preparation.domain.mediatorPattern.CourseImplMediator
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MediatorConfiguration {

    @Bean
    fun courseRemovedHandler(): Component {
        return CourseRemovedHandler()
    }



    @Bean
    fun courseMediator(): CourseMediator{
        val mediator = CourseImplMediator()
        mediator.addUser(CourseRemovedDomainEvent.NAME,courseRemovedHandler())
        return mediator

    }
}