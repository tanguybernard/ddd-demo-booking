package com.example.booking.booking.infrastructure

import com.example.booking.booking.domain.Hotel
import com.example.booking.booking.domain.HotelAgenda
import com.example.booking.booking.domain.HotelRepository
import java.util.*
import kotlin.collections.ArrayList

class InMemoryHotelRepository : HotelRepository {

    val hotels = ArrayList<Hotel>()
    override fun storeHotel(hotel: Hotel) {
        hotels.add(hotel)
    }

    override fun getHotelWithAvailableRoomForDate(startDate: Date): List<Hotel> {
        val hotelsFound = ArrayList<Hotel>()
        hotels.forEach {
            val rooms = it.getRoomAvailableFor(startDate)
            if(rooms.isNotEmpty()) {
                val agenda = HotelAgenda()
                agenda.setRooms(startDate, rooms)
                hotelsFound.add(Hotel(it.hotelId, it.name, agenda))
            }
        }
        return hotelsFound
    }


}
