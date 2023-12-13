package com.example.training

import com.example.training.preparation.infrastructure.redis.RedisMessagePublisher
import com.example.training.preparation.infrastructure.redis.RedisMessageSubscriber
import com.example.training.shared.infrastructure.MessagePublisher
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter


@Configuration
@ConditionalOnProperty(name = ["app.redis.enabled"], havingValue = "true")
class RedisConfiguration {

    @Value("\${spring.data.redis.host}")
    lateinit var redisHost: String

    @Value("\${spring.data.redis.port}")
    lateinit var redisPort: String


    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration(redisHost, redisPort.toInt())
        return LettuceConnectionFactory(config)
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
    fun messagePublisher(): MessagePublisher {
        return RedisMessagePublisher(redisTemplate(), topic())
    }

    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("messageQueue")
    }

}

