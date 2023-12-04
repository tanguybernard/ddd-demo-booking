package com.example.training.preparation.infrastructure.redis

import com.example.training.shared.infrastructure.IntegrationEvent
import java.io.Serializable
import java.time.LocalDate



class SessionCourseIntegrationEvent : Serializable, IntegrationEvent {


    val occurredOn: LocalDate = LocalDate.now();
    override var id: String = "";
    override var name: String = ""

}