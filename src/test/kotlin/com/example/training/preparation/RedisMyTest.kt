package com.example.training.preparation

import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.preparation.infrastructure.redis.RedisMessagePublisher
import com.example.training.shared.infrastructure.MessagePublisher
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest
class RedisMyTest(

) {
    @Autowired
    private lateinit var redisPublisher: MessagePublisher




    @Test
    fun removeTrainingSession(){
        val message = "Message Super" + UUID.randomUUID()
        redisPublisher.publish(message)
    }
}
