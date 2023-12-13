package com.example.training.preparation.integration

import com.example.training.AbstractTestcontainersIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest (classes = [RedisAutoConfiguration::class])
@Testcontainers
class RedisIntegrationTest : AbstractTestcontainersIntegrationTest() {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @Test
    fun sendMessageToRedis() {
        // Envoyer un message à Redis
        val key = "exampleKey"
        val message = "Hello, Redis!"
        redisTemplate.opsForValue().set(key, message)

        // Récupérer le message depuis Redis et vérifier s'il correspond
        val retrievedMessage = redisTemplate.opsForValue().get(key)
        assert(retrievedMessage == message) { "Le message récupéré ne correspond pas au message envoyé." }
    }
}