package com.example.training.registration.domain.session.events

import com.example.training.registration.domain.session.SessionId
import com.example.training.shared.DomainEvent


class UserRefusedForSessionEvent(val sessionId: SessionId, val userEmail: String): DomainEvent {

    override fun getName(): String {
        return "USER_REFUSED_FOR_SESSION"
    }
}

