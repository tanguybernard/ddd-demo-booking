package com.example.training.shared

interface DomainEvent {
    fun getName(): String
    fun getAggregateRootId(): String
}