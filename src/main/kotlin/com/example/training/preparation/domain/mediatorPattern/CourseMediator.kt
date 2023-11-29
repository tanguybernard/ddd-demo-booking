package com.example.training.preparation.domain.mediatorPattern

import com.example.training.shared.DomainEvent

interface CourseMediator {

    fun sendMessage(event: DomainEvent, sender: Component)
    fun addUser(name: String, component: Component)
}