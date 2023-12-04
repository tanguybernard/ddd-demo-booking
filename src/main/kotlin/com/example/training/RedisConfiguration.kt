package com.example.training

import com.example.training.preparation.infrastructure.redis.RedisMessagePublisher
import com.example.training.preparation.infrastructure.redis.RedisMessageSubscriber
import com.example.training.shared.infrastructure.MessagePublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter


@Configuration
class RedisConfiguration {


    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = redisConnectionFactory()
        return template
    }



    @Bean
    fun messageListener(): MessageListenerAdapter {
        return MessageListenerAdapter(RedisMessageSubscriber())
    }

    @Bean
    fun redisContainer(): RedisMessageListenerContainer? {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory())
        container.addMessageListener(messageListener(), topic())
        return container
    }

    @Bean
    fun redisPublisher(): MessagePublisher {
        return RedisMessagePublisher(redisTemplate(), topic())
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("messageQueue")
    }

}

