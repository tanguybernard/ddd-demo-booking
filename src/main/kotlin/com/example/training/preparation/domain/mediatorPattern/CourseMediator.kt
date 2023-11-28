package com.example.training.preparation.domain.mediatorPattern

import com.example.training.shared.DomainEvent

interface CourseMediator<T:DomainEvent> {

    fun sendMessage(event: T, sender: Component<T>)
    fun addUser(component: Component<T>)
}