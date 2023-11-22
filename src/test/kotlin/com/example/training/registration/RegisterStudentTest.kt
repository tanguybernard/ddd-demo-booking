package com.example.training.registration

import com.example.training.registration.domain.session.Session
import com.example.training.registration.domain.session.SessionId
import com.example.training.registration.domain.session.SessionRepository
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
        sessionRepository = InMemorySessionRepository();
        sessionId = SessionId(UUID.randomUUID().toString())
        session = Session.create(sessionId)
        sessionRepository.create(session)
    }

    @Test
    fun `register user for training session`() {

        val name = "John Doe"
        val email = "john.doe@gmail.com"

        val placeExpected = Place(name, email, PlaceStatus.REGISTRATION_REQUEST);

        val useCase = RegisterStudentForSession(sessionRepository)
        useCase.execute(RegisterStudentCommand(name, email, session.sessionId.value))

        assertThat(session.getPlaces().first { it.email == email }).isEqualTo(placeExpected);


    }
}