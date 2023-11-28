package com.example.training.preparation.application.course

import com.example.training.preparation.domain.CourseRemovedDomainEvent
import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.shared.DomainEvent

class CourseRemovedHandler():
    Component<CourseRemovedDomainEvent> {

    override fun send(msg: DomainEvent) {
        TODO("Not yet implemented")
    }

    override fun receive(msg: CourseRemovedDomainEvent) {

        //TODO
        //need to send  integration event to registration
        println("Course removed FRom Handler")
    }


}