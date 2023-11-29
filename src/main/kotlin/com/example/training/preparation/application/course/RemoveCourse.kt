package com.example.training.preparation.application.course

import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.TrainingId
import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.shared.DomainEvent

class RemoveCourse(
    private val courseRepository: TrainingCourseRepository,
    private var courseMediator: CourseMediator
): Component {

    fun execute(courseId: String){

        val course = courseRepository.getTrainingCourseBy(TrainingId(courseId))

        course.remove()
        //courseRepository.remove(course)

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