package com.example.training.registration.domain.session.events

import com.example.training.registration.domain.session.SessionId
import com.example.training.shared.DomainEvent


class UserRefusedForSessionEvent(val sessionId: SessionId): DomainEvent

