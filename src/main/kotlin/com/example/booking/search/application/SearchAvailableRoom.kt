package com.example.booking.search.application

import com.example.booking.search.domain.hotel.Hotel
import com.example.booking.search.domain.hotel.HotelRepository
import com.example.booking.search.domain.hotel.Period

class SearchAvailableRoom(val hotelRepository: HotelRepository) {

    fun execute(period: Period): List<Hotel> {

        return hotelRepository.getHotelWithAvailableRoomForDate(period.startDate)


    }

}
