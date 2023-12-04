package com.example.training.shared.infrastructure

interface MessagePublisher {
    fun <T :IntegrationEvent> publish(integrationEvent: T)
}