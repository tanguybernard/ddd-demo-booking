package com.example.training.registration.domain.session

import com.example.training.registration.Place

class Session private constructor(val sessionId: SessionId) {
    private var places: ArrayList<Place> = arrayListOf()

    fun getPlaces(): List<Place> {
        return this.places
    }

    fun addPlace(place: Place) {
        val placeAlreadyExist = places.find { it.email == place.email }
        if(placeAlreadyExist != null) {
            throw PlaceWithSameEmailAlreadyRegistered()
        }
        else {

            this.places.add(place)
        }
    }

    companion object {
        fun create(sessionId: SessionId): Session {

            return Session(sessionId)

        }
    }

}