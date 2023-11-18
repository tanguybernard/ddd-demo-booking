package com.example.booking.search.domain.hotel

import com.example.booking.search.infrastructure.NoRoomAvailable
import java.util.*

data class Hotel(val hotelId: HotelId, val name: String, val hotelAgenda: HotelAgenda) {
    fun getRoomAvailableFor(date: Date) : List<Room> {
        return hotelAgenda.dayList.getOrDefault(date, listOf())
            .filter { room: Room -> room.status == RoomStatus.AVAILABLE }
    }

    fun selectRoom(date: Date, room: Room) {
        val rooms = hotelAgenda.dayList[date]?.filter {
            it.status == RoomStatus.AVAILABLE && it.capacity == room.capacity
        }

            if(rooms != null && rooms.isNotEmpty()) {
                rooms[0].select()
                //TODO this.register(RoomSelectedDomainEvent()) ??? useful ?
            }
            else {
                throw NoRoomAvailable()
            }

    }

}
