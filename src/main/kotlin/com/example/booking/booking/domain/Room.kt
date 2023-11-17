package com.example.booking.booking.domain


class Room(val roomId: RoomId, val status: RoomStatus) {
}


enum class RoomStatus {
    AVAILABLE,
    BOOKED
}