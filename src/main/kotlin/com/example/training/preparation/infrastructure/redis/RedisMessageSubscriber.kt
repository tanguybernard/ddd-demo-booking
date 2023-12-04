package com.example.training.preparation.infrastructure.redis

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener


class RedisMessageSubscriber : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        messageList.add(message.toString())
        println("Message received: $message")
    }

    companion object {
        var messageList: MutableList<String> = ArrayList()
    }
}