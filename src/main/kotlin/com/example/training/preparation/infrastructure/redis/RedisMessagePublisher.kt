package com.example.training.preparation.infrastructure.redis

import com.example.training.shared.infrastructure.MessagePublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic


class RedisMessagePublisher(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val topic: ChannelTopic) : MessagePublisher {


    override fun publish(message: String) {
        redisTemplate.convertAndSend(topic.topic, message)
    }
}