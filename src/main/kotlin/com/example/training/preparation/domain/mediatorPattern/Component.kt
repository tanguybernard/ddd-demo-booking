package com.example.training.preparation.domain.mediatorPattern

import com.example.training.shared.DomainEvent


interface  Component {

    //fun setMediator(mediator: PreparationMediator<T>)
    fun send(event: DomainEvent)
    fun receive(event: DomainEvent)
}