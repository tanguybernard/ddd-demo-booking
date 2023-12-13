package com.example.training.registration.integration

import com.example.training.AbstractTestcontainersIntegrationTest
import com.example.training.RedisConfiguration
import com.example.training.TrainingApplication
import com.example.training.registration.DomainEventsConfiguration
import com.example.training.registration.application.RefuseStudentForSession
import com.example.training.registration.application.UserRefusedForSessionDomainEventHandler
import com.example.training.registration.domain.session.*
import com.example.training.registration.infrastructure.InMemorySessionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.UUID

@Testcontainers
@TestPropertySource(properties = ["app.redis.enabled=true"])
@SpringBootTest(classes = [DomainEventsConfiguration::class, RedisConfiguration::class])
class RefuseStudentTest: AbstractTestcontainersIntegrationTest() {


    @Autowired
    private lateinit var sessionRepository: SessionRepository

    @Autowired
    private lateinit var refuseStudentForSession: RefuseStudentForSession


    private lateinit var session : Session
    private lateinit var sessionId : SessionId
    @BeforeEach
    fun before() {
        sessionId = SessionId(UUID.randomUUID().toString())
        session = Session.create(sessionId, SessionTitle("DDD Training"))
        sessionRepository.create(session)
    }

    @Test
    fun `refuse user for training session`() {

        //before
        val name = "John Doe"
        val email = "john.doe@gmail.com"
        val place = Place(name, email, PlaceStatus.REGISTRATION_REQUESTED)
        session.addUser(place)
        sessionRepository.saveSession(session)
        //when
        refuseStudentForSession.execute(email, session.sessionId.value)
        //then
        val sessionWithoutJohnDoeUser = sessionRepository.getSessionBy(sessionId)
        assertThat(sessionWithoutJohnDoeUser.getPlaces()).hasSize(0)


        //Check mail send !

    }
}