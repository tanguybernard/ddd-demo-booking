package com.example.booking.preparation

import com.example.booking.preparation.application.hotel.CreateHotel
import com.example.booking.preparation.application.hotel.CreateHotelCommand
import com.example.booking.preparation.domain.hotel.HotelId
import com.example.booking.preparation.domain.hotel.HotelName
import com.example.booking.preparation.infrastructure.stubs.InMemoryHotelRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.UUID

class CreateHotelTest {

    @Test
    fun createHotel() {

        //Given
        val hotelName = "The Grand Budapest Hotel"
        val id = UUID.randomUUID().toString()
        val repository = InMemoryHotelRepository()
        val useCase = CreateHotel(repository) { }
        val command = CreateHotelCommand(id, hotelName)
        //When
        useCase.execute(command)

        //Then
        val hotelSaved = repository.getHotelBy(HotelId(id))
        assertThat(hotelSaved.hotelName).isEqualTo(HotelName(hotelName))

    }
}