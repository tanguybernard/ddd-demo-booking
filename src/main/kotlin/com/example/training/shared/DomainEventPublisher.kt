package com.example.training.shared


class DomainEventPublisher <TT:DomainEvent>  {

    companion object  {

        private var mInstance: DomainEventPublisher<DomainEvent>? = null

        //returns the singleton object
        fun getInstance(): DomainEventPublisher<DomainEvent> {
            //use myArgs to for your stuffs
            if (mInstance == null)
                mInstance = DomainEventPublisher()
            return mInstance as DomainEventPublisher
        }

    }



    private var handlersMap:  HashMap<String, List<DomainEventSubscriber<TT>>>
            = hashMapOf()

    fun register(
        subscriber: DomainEventSubscriber<TT>,
        domainEventName: String
    ) {
        val handlers: List<DomainEventSubscriber<TT>>? = handlersMap[domainEventName]
        if (handlers != null) {
            handlersMap[domainEventName] = handlers.plus(subscriber)
        } else {
            handlersMap[domainEventName] = listOf(subscriber)
        }
    }

    fun clearHandlers() {
        handlersMap = HashMap()
    }

    fun dispatch(event: TT) {
        val eventClassName = event::class.toString()
        if (handlersMap[eventClassName] != null) {
            val handlers = handlersMap[eventClassName]
            if (handlers != null) {
                for (handler in handlers) {
                    handler.handleEvent(event)
                }
            }
        }
    }

}