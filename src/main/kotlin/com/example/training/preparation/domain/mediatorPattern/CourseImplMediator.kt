package com.example.training.preparation.domain.mediatorPattern

import com.example.training.shared.DomainEvent

// Concrete Mediator, send message to component
class CourseImplMediator <T:DomainEvent> : CourseMediator<T> {

    private var components: List<Component<T>> ;

    init {
        components= ArrayList();
    }

    override fun sendMessage(event: T, sender: Component<T>) {
        // Broadcast the message to all users except the sender
        for (user in components) {
            if (user !== sender) {
                user.receive(event)
            }
        }
    }

    override fun addUser(component: Component<T>) {
        this.components = this.components.plus(component)
    }
}