package com.example.booking.booking.domain

import java.util.*

interface HotelRepository {

    fun storeHotel(hotel: Hotel)
    fun getHotelWithAvailableRoomForDate(startDate: Date): List<Hotel>

}
