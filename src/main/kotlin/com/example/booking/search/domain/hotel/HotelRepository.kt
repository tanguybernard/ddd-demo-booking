package com.example.booking.search.domain.hotel

import java.util.*

interface HotelRepository {

    fun storeHotel(hotel: Hotel)
    fun getHotelWithAvailableRoomForDate(startDate: Date): List<Hotel>
    fun selectRoomWith(hotelId: HotelId, roomCapacity: Int, date: Date)
    fun getHotel(hotelId: HotelId): Hotel


}
