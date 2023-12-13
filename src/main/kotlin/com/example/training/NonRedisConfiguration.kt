package com.example.training

import com.example.training.preparation.infrastructure.redis.RedisMessagePublisher
import com.example.training.preparation.infrastructure.redis.RedisMessageSubscriber
import com.example.training.preparation.infrastructure.stubs.FakeMessagePublisher
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
@ConditionalOnProperty(name = ["app.redis.enabled"], havingValue = "false")
class NonRedisConfiguration {


    @Bean
    fun messagePublisher(): MessagePublisher {
        return FakeMessagePublisher();
    }



}

