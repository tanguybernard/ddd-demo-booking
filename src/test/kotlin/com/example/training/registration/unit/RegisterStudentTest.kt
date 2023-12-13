package com.example.training.registration.unit

import com.example.training.registration.application.RegisterStudentCommand
import com.example.training.registration.application.RegisterStudentForSession
import com.example.training.registration.domain.session.*
import com.example.training.registration.infrastructure.InMemorySessionRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID

class RegisterStudentTest {


    private lateinit var sessionRepository : SessionRepository

    private lateinit var session : Session
    private lateinit var sessionId : SessionId
    @BeforeEach
    fun before() {
        sessionRepository = InMemorySessionRepository()
        sessionId = SessionId(UUID.randomUUID().toString())
        session = Session.create(sessionId, SessionTitle("DDD Training"))
        sessionRepository.create(session)
    }

    @Test
    fun `register user for training session`() {

        val name = "John Doe"
        val email = "john.doe@gmail.com"

        val placeExpected = Place(name, email, PlaceStatus.REGISTRATION_REQUESTED)

        val useCase = RegisterStudentForSession(sessionRepository)
        useCase.execute(RegisterStudentCommand(name, email, session.sessionId.value))
        val sessionWithNewUser = sessionRepository.getSessionBy(session.sessionId)
        assertThat(sessionWithNewUser.getPlaces().first { it.email == email }).isEqualTo(placeExpected)


    }
}