package com.example.training.registration.domain.session

data class Place(val name: String, val email: String, val status: PlaceStatus) {

}

enum class PlaceStatus {
    REGISTRATION_REQUESTED
}
