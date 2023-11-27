package com.example.training.registration

import com.example.training.registration.application.UserRefusedForSessionHandler
import com.example.training.registration.domain.session.events.UserRefusedForSessionEvent
import com.example.training.shared.DomainEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainEventsConfiguration {


    @Bean
    fun configureEvents(){
        return DomainEventPublisher.getInstance().register(
            UserRefusedForSessionHandler("toto"), UserRefusedForSessionEvent::class.toString()
        )
    }

}
