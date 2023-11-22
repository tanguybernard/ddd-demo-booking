package com.example.training.registration

import com.example.training.registration.domain.session.SessionId
import com.example.training.registration.domain.session.SessionRepository

class RegisterStudentForSession(private val sessionRepository: SessionRepository) {


    fun execute(command: RegisterStudentCommand) {
        val session = sessionRepository.getSessionBy(SessionId( command.sessionId))

        session.addPlace(Place(command.name, command.email, PlaceStatus.REGISTRATION_REQUEST))
    }

}
