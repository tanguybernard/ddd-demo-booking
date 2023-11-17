package com.example.booking.booking.domain

import java.util.*

data class Hotel(val hotelId: HotelId, val name: String, val hotelAgenda: HotelAgenda) {
    fun getRoomAvailableFor(date: Date) : List<Room> {

        return hotelAgenda.dayList.getOrDefault(date, listOf())
            .filter { room: Room -> room.status == RoomStatus.AVAILABLE }
    }

}
