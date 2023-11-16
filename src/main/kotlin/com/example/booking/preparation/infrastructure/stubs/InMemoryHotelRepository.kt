package com.example.booking.preparation.infrastructure.stubs

import com.example.booking.preparation.domain.hotel.Hotel
import com.example.booking.preparation.domain.hotel.HotelId
import com.example.booking.preparation.domain.hotel.HotelRepository

class InMemoryHotelRepository : HotelRepository {

    private var map= HashMap<HotelId, Hotel>()
    override fun getHotelBy(id: HotelId): Hotel {
        return map.get(id)!!
    }

    override fun createHotel(hotel: Hotel) : Hotel {
        map[hotel.hotelId] = hotel
        return hotel
    }

    override fun update(hotel: Hotel) {
        map[hotel.hotelId] = hotel
    }


}