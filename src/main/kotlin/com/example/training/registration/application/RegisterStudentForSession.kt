package com.example.training.registration.application

import com.example.training.registration.domain.session.Place
import com.example.training.registration.domain.session.PlaceStatus
import com.example.training.registration.domain.session.SessionId
import com.example.training.registration.domain.session.SessionRepository

class RegisterStudentForSession(private val sessionRepository: SessionRepository) {


    fun execute(command: RegisterStudentCommand) {
        val session = sessionRepository.getSessionBy(SessionId( command.sessionId))

        session.addUser(Place(command.name, command.email, PlaceStatus.REGISTRATION_REQUESTED))

        sessionRepository.saveSession(session)
    }

}
