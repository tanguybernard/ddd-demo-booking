package com.example.booking.preparation.application.hotel

import com.example.booking.preparation.domain.hotel.Hotel
import com.example.booking.preparation.domain.hotel.HotelCreated
import com.example.booking.preparation.domain.hotel.HotelRepository
import org.springframework.context.event.EventListener

class HotelCreatedHandler(
    private val hotelRepository: HotelRepository
) {


    @EventListener(HotelCreated::class)
    fun onHotelCreate(hotelCreated: HotelCreated) {
        val hotel: Hotel = hotelRepository.getHotelBy(hotelCreated.hotelId)

        //send mail
    }
}