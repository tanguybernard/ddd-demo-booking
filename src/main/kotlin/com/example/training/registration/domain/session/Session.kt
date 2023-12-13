package com.example.training.registration.domain.session

import com.example.training.registration.domain.session.events.UserRefusedForSessionEvent
import com.example.training.shared.AggregateRoot

class Session private constructor(val sessionId: SessionId, val title: SessionTitle) : AggregateRoot<SessionId>(sessionId) {
    private var places: ArrayList<Place> = arrayListOf()

    fun getPlaces(): List<Place> {
        return this.places
    }

    fun addUser(place: Place) {
        val placeAlreadyExist = places.find { it.email == place.email }
        if(placeAlreadyExist != null) {
            throw PlaceWithSameEmailAlreadyRegistered()
        }
        else {

            this.places.add(place)
        }
    }

    fun removeUserBy(email: String) {
        this.places = this.places.filter { it.email != email } as ArrayList<Place>
        this.record(UserRefusedForSessionEvent(this.sessionId, email));
    }

    companion object {
        fun create(sessionId: SessionId, title: SessionTitle): Session {
            return Session(sessionId, title)
        }
    }

}