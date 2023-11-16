package com.example.booking.preparation.domain.hotel

interface HotelRepository {

    fun getHotelBy(id: HotelId) : Hotel
    fun createHotel(hotel: Hotel): Hotel

    fun update(hotel: Hotel)

}
