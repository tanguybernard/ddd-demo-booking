package com.example.training.preparation.application.course

import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.CourseId
import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.preparation.domain.course.SessionId
import com.example.training.shared.DomainEvent

class RemoveSession(
    private val trainingCourseRepository: TrainingCourseRepository,
    private var courseMediator: CourseMediator
): Component {

    fun execute(courseId: String, sessionId: String){

        val course = trainingCourseRepository.getTrainingCourseBy(CourseId(courseId))

        course.removeSession(SessionId(sessionId))
        trainingCourseRepository.save(course)

        course.pullDomainEvents().forEach {
            this.send(it)
        }
    }


    override fun send(event: DomainEvent) {
        courseMediator.sendMessage(event,this)
    }

    override fun receive(event: DomainEvent) {
        TODO("Not yet implemented")
    }

}