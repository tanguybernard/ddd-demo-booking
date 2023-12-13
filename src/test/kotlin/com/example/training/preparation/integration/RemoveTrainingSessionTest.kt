package com.example.training.preparation.integration

import com.example.training.AbstractTestcontainersIntegrationTest
import com.example.training.NonRedisConfiguration
import com.example.training.RedisConfiguration
import com.example.training.preparation.application.course.RemoveSession
import com.example.training.preparation.configuration.PreparationConfiguration
import com.example.training.preparation.domain.course.*
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.preparation.infrastructure.redis.RedisMessagePublisher
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainingRepository
import com.example.training.registration.DomainEventsConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.LocalDate
import java.util.*



@SpringBootTest (classes = [PreparationConfiguration::class, NonRedisConfiguration::class])
@TestPropertySource(properties = ["app.redis.enabled=false"])
class RemoveTrainingSessionTest()
 {
    @Autowired
    private lateinit var courseMediator: CourseMediator

    @Autowired
    private lateinit var trainingCourseRepository: TrainingCourseRepository

    private val trainerId = "#tbe"
    private lateinit var trainingId: String
    private lateinit var sessionId: String

    @BeforeEach
    fun setUp() {

        this.trainingId = UUID.randomUUID().toString()

        val course = TrainingCourse(
                CourseId(this.trainingId),
                TrainingName("DDD Training"),
                3
        )


        val startDate = LocalDate.now().plusDays(3)
        val endDate = startDate.plusDays(3)
        sessionId = UUID.randomUUID().toString()
        course.addSession(
            Session(
                SessionId(sessionId),
                TrainerId(trainerId),
                startDate,
                endDate
            )
        )
        trainingCourseRepository.save(course)
    }


    @Test
    @DirtiesContext
    fun removeTrainingSession(){

        val useCase = RemoveSession(this.trainingCourseRepository, courseMediator)

        useCase.execute(this.trainingId, this.sessionId)

        //TODO update Test
        assertThat(true).isTrue()

    }
}
