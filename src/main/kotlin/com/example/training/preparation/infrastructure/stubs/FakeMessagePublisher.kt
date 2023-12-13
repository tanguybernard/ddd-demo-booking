package com.example.training.preparation.infrastructure.stubs

import com.example.training.shared.infrastructure.MessagePublisher

class FakeMessagePublisher : MessagePublisher {
    override fun publish(message: String) {
        println(message)
    }
}