package com.example.booking.booking

import com.example.booking.booking.domain.Hotel
import com.example.booking.booking.domain.HotelRepository
import com.example.booking.booking.domain.Period

class SearchAvailableRoom(val hotelRepository: HotelRepository) {

    fun execute(period: Period): List<Hotel> {

        return hotelRepository.getHotelWithAvailableRoomForDate(period.startDate)


    }

}
