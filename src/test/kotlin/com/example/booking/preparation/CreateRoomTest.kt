package com.example.booking.preparation

import com.example.booking.preparation.application.room.CreateRoom
import com.example.booking.preparation.application.room.CreateRoomCommand
import com.example.booking.preparation.application.room.RoomDomainService
import com.example.booking.preparation.domain.hotel.Hotel
import com.example.booking.preparation.domain.hotel.HotelId
import com.example.booking.preparation.domain.hotel.HotelName
import com.example.booking.preparation.infrastructure.stubs.InMemoryHotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class CreateRoomTest {

    @Test
    fun createRoom(){
        val hotelName = "The Grand Budapest Hotel"
        val hotelId = UUID.randomUUID().toString()
        val hotel = Hotel(HotelId(hotelId), HotelName(hotelName))
        val hotelRepository = InMemoryHotelRepository()
        hotelRepository.createHotel(hotel)
        val roomNumber = 1
        val capacity = 2
        val createRoomCommand = CreateRoomCommand(hotelId, roomNumber, capacity)

        val roomDomainService = RoomDomainService(hotelRepository)

        //When
        val useCase = CreateRoom(roomDomainService)
        useCase.execute(createRoomCommand)

        //then
        val hotelUpdated = hotelRepository.getHotelBy(HotelId(hotelId))
        assertThat(hotelUpdated.getListRoom()).hasSize(1)


    }
}