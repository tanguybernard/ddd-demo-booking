package com.example.training.shared



open class AggregateRoot<Id>(id: Id) : Entity<Id>(id) {
    private var domainEvents = mutableListOf<DomainEvent>()

    fun pullDomainEvents(): List<DomainEvent> {
        val domainEvents = domainEvents
        this.domainEvents = listOf<DomainEvent>().toMutableList()
        return domainEvents
    }

    protected fun record(domainEvent: DomainEvent) {
        domainEvents.add(domainEvent)
    }
}