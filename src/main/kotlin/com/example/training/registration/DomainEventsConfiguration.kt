package com.example.training.registration

import com.example.training.registration.application.RefuseStudentForSession
import com.example.training.registration.application.UserRefusedForSessionDomainEventHandler
import com.example.training.registration.domain.session.SessionRepository
import com.example.training.registration.domain.session.events.UserRefusedForSessionEvent
import com.example.training.registration.infrastructure.InMemorySessionRepository
import com.example.training.registration.infrastructure.fake.FakeEmailSender
import com.example.training.shared.DomainEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainEventsConfiguration {


    @Bean
    fun sessionRepository(): SessionRepository {
        return InMemorySessionRepository()
    }

    @Bean
    fun refuseStudentForSession(): RefuseStudentForSession {
        return RefuseStudentForSession(sessionRepository())
    }

    @Bean
    fun userRefusedForSessionHandler(): UserRefusedForSessionDomainEventHandler {
        return UserRefusedForSessionDomainEventHandler(
            FakeEmailSender(), sessionRepository()
        )
    }


    @Bean
    fun configureEvents(){
        return DomainEventPublisher.getInstance().register(
            userRefusedForSessionHandler(),
            UserRefusedForSessionEvent::class.toString()
        )
    }

}
