package com.example.booking.search

import com.example.booking.search.application.SelectRoom
import com.example.booking.search.application.SelectRoomCommand
import com.example.booking.search.domain.hotel.*
import com.example.booking.search.infrastructure.InMemoryHotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class SelectRoomTest {

    @Test
    fun `select room for one night`() {


        val currentDate = Date()
        val c = Calendar.getInstance()
        c.time = currentDate
        c.add(Calendar.MONTH, 1)
        c.set(Calendar.HOUR, 20)
        val currentDatePlusOneMonth = c.time;


        val agenda = HotelAgenda()
        val capacity = 3

        agenda.setRooms(
            currentDatePlusOneMonth,
            listOf(Room(capacity, RoomStatus.AVAILABLE), Room(2, RoomStatus.AVAILABLE))
        )
        val hotelId = UUID.randomUUID().toString()
        val hotel = Hotel(
            HotelId(hotelId),
            "Grand Budapest Hotel",
            agenda
        )

        val hotelRepository = InMemoryHotelRepository()
        hotelRepository.storeHotel(hotel)


        val useCase = SelectRoom(hotelRepository)
        val command = SelectRoomCommand(hotelId, capacity, currentDatePlusOneMonth)
        useCase.execute(command)

        //hotelRepository.selectRoomWith(HotelId(hotelId), capacity, currentDatePlusOneMonth)
        val hotelWithOneRoomSelected = hotelRepository.getHotel(HotelId(hotelId))

        assertThat(hotelWithOneRoomSelected.getRoomAvailableFor(currentDatePlusOneMonth))
            .isEqualTo(listOf(Room(2, RoomStatus.AVAILABLE)))



    }

}