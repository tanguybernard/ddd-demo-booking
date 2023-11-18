package com.example.booking.search.application

import com.example.booking.search.domain.hotel.HotelId
import com.example.booking.search.domain.hotel.HotelRepository

class SelectRoom(private val hotelRepository: HotelRepository) {

    fun execute(command: SelectRoomCommand) {
        // TODO hotelRepository.selectRoomForDate()
        // reflexion ? Not Sure
        //event -> Room handler (RoomSelected)

        hotelRepository.selectRoomWith(
            HotelId( command.hotelId), command.capacity, command.date
        )

    }

}
