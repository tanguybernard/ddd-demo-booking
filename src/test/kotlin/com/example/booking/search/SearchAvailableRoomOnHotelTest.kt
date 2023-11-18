package com.example.booking.search

import com.example.booking.search.application.SearchAvailableRoom
import com.example.booking.search.domain.hotel.Hotel
import com.example.booking.search.domain.hotel.HotelAgenda
import com.example.booking.search.domain.hotel.HotelId
import com.example.booking.search.domain.hotel.Period
import com.example.booking.search.domain.hotel.Room
import com.example.booking.search.domain.hotel.RoomId
import com.example.booking.search.domain.hotel.RoomStatus
import com.example.booking.search.infrastructure.InMemoryHotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*


class SearchAvailableRoomOnHotelTest {

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
            listOf(Room(3, RoomStatus.AVAILABLE))
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