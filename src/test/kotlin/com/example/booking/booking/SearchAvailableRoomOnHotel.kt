package com.example.booking.booking

import com.example.booking.booking.domain.*
import com.example.booking.booking.infrastructure.InMemoryHotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*


class SearchAvailableRoomOnHotel {

    @Test
    fun `search available room for one night`(){

        //Given
        val currentDate = Date()
        val c = Calendar.getInstance()
        c.time = currentDate
        c.add(Calendar.MONTH, 1)
        c.set(Calendar.HOUR, 20)
        val currentDatePlusOneMonth = c.time;


        val agenda = HotelAgenda()
        agenda.setRooms(
            currentDatePlusOneMonth,
            listOf(Room(RoomId("1"), RoomStatus.AVAILABLE))
            )

        val hotel = Hotel(
            HotelId(UUID.randomUUID().toString()),
            "Grand Budapest Hotel",
            agenda
        )

        val hotelRepository = InMemoryHotelRepository()
        hotelRepository.storeHotel(hotel)
        val period = Period(currentDatePlusOneMonth, currentDatePlusOneMonth)

        val useCase = SearchAvailableRoom(hotelRepository)

        //Given
        val found = useCase.execute(period)

        //Then
        assertThat(found).hasSize(1)


    }
}