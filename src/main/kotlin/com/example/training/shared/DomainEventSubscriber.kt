package com.example.training.shared


interface DomainEventSubscriber< out T:DomainEvent> {
    fun handleEvent(aDomainEvent: @UnsafeVariance T)
}