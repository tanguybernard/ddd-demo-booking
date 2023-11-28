package com.example.training.preparation

import com.example.training.preparation.application.course.CourseRemovedHandler
import com.example.training.preparation.domain.CourseRemovedDomainEvent
import com.example.training.preparation.domain.mediatorPattern.CourseImplMediator
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MediatorConfiguration {

    @Bean
    fun courseRemovedHandler(): CourseRemovedHandler {
        return CourseRemovedHandler();
    }



    @Bean
    fun courseMediator(): CourseMediator<CourseRemovedDomainEvent>{
        val mediator = CourseImplMediator<CourseRemovedDomainEvent>()
        mediator.addUser(courseRemovedHandler())
        return mediator;

    }
}