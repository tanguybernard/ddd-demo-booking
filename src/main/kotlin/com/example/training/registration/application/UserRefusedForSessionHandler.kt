package com.example.training.registration.application

import com.example.training.registration.domain.session.events.UserRefusedForSessionEvent
import com.example.training.shared.DomainEventSubscriber

class UserRefusedForSessionHandler(val user: String): DomainEventSubscriber<UserRefusedForSessionEvent> {
    override fun handleEvent(aDomainEvent: UserRefusedForSessionEvent) {
        print("Into Handler for session user")
        //TODO
    }
}