package com.example.booking.preparation.application.room

import com.example.booking.preparation.domain.hotel.HotelId
import com.example.booking.preparation.domain.hotel.HotelRepository
import com.example.booking.preparation.domain.room.Room

class CreateRoom(private val roomDomainService: RoomDomainService) {

     fun execute(command: CreateRoomCommand) {

         roomDomainService.createRoomIfNumberDoesNotExist(
             Room(RoomNumber(command.roomNumber), command.capacity),
             HotelId( command.hotelId)
         )

     }
}
