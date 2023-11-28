package com.example.training.registration

import com.example.training.registration.application.RegisterStudentCommand
import com.example.training.registration.application.RegisterStudentForSession
import com.example.training.registration.domain.session.*
import com.example.training.registration.infrastructure.InMemorySessionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import java.util.UUID

@SpringBootTest
class RefuseStudentTest {


    private lateinit var sessionRepository : SessionRepository

    private lateinit var session : Session
    private lateinit var sessionId : SessionId
    @BeforeEach
    fun before() {
        sessionRepository = InMemorySessionRepository();
        sessionId = SessionId(UUID.randomUUID().toString())
        session = Session.create(sessionId, SessionTitle("DDD Training"))
        sessionRepository.create(session)
    }

    @Test
    fun `register user for training session`() {

        val name = "John Doe"
        val email = "john.doe@gmail.com"

        val place = Place(name, email, PlaceStatus.REGISTRATION_REQUEST);

        session.addUser(place)
        sessionRepository.saveSession(session);

        val useCase = RefuseStudentForSession(sessionRepository)
        useCase.execute(email, session.sessionId.value)

        val sessionWithoutJohnDoeUser = sessionRepository.getSessionBy(session.sessionId)



        assertThat(sessionWithoutJohnDoeUser.getPlaces()).hasSize(0)


    }
}