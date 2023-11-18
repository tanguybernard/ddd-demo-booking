package com.example.booking.search.domain.hotel


data class Room(val capacity: Int, var status: RoomStatus) {
    fun select() {
        status = RoomStatus.SELECTED
    }
}


enum class RoomStatus {
    AVAILABLE,
    SELECTED,
    BOOKED
}