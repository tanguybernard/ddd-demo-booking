package com.example.booking.preparation.application.room;

import com.example.booking.preparation.domain.hotel.HotelId
import com.example.booking.preparation.domain.hotel.HotelRepository
import com.example.booking.preparation.domain.room.Room

class RoomDomainService(private val hotelRepository: HotelRepository) {


    fun createRoomIfNumberDoesNotExist(room: Room, hotelId: HotelId){

        val foundHotel= hotelRepository.getHotelBy(hotelId)

        if(foundHotel.has(room)) {
           throw RoomNumberAlreadyExist()
        }

        foundHotel.addRoom(room)
        hotelRepository.update(foundHotel)

    }

}
