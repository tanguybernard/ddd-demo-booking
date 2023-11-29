package com.example.training.preparation.domain.mediatorPattern

import com.example.training.shared.DomainEvent

// Concrete Mediator, send message to component
class CourseImplMediator: CourseMediator {

    private var components: HashMap<String, List<Component>> = HashMap()

    override fun sendMessage(event: DomainEvent, sender: Component) {
        // Broadcast the message to all users except the sender

        components[event.getName()]?.forEach { user ->
            if(user != sender ) {
                user.receive(event)
            }
        }
    }

    override fun addUser(name: String, component: Component) {
        this.components[name] = this.components[name]?.plus(component) ?: listOf(component)
    }
}