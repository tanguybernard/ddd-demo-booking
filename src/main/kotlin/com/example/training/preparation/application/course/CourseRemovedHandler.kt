package com.example.training.preparation.application.course

import com.example.training.preparation.domain.mediatorPattern.Component
import com.example.training.shared.DomainEvent

class CourseRemovedHandler: Component {

    override fun send(event: DomainEvent) {
        TODO("Not yet implemented")
    }

    override fun receive(event: DomainEvent) {

        //TODO
        //need to send  integration event to registration
        println("Course removed FRom Handler")
    }


}