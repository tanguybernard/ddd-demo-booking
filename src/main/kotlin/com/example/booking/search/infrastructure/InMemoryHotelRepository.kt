package com.example.booking.search.infrastructure

import com.example.booking.search.domain.hotel.HotelNotFound
import com.example.booking.search.domain.hotel.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class InMemoryHotelRepository : HotelRepository {

    val hotels = HashMap<HotelId, Hotel>()
    override fun storeHotel(hotel: Hotel) {
        hotels.put(hotel.hotelId, hotel)
    }

    override fun getHotelWithAvailableRoomForDate(startDate: Date): List<Hotel> {
        val hotelsFound = ArrayList<Hotel>()
        hotels.forEach { (_, value) ->
            val rooms = value.getRoomAvailableFor(startDate)
            if(rooms.isNotEmpty()) {
                val agenda = HotelAgenda()
                agenda.setRooms(startDate, rooms)
                hotelsFound.add(Hotel(value.hotelId, value.name, agenda))
            }
        }
        return hotelsFound
    }

    override fun selectRoomWith(hotelId: HotelId, roomCapacity: Int, date: Date) {

        val hotelFound = hotels[hotelId]
        hotelFound?.selectRoom(date, Room(roomCapacity, RoomStatus.AVAILABLE))

    }

    override fun getHotel(hotelId: HotelId): Hotel {
        return hotels[hotelId] ?: throw HotelNotFound()

    }


}
