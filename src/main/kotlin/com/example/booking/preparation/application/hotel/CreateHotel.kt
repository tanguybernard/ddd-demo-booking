package com.example.booking.preparation.application.hotel

import com.example.booking.preparation.domain.hotel.*
import org.springframework.context.ApplicationEventPublisher


class CreateHotel(private val repository: HotelRepository, private val eventPublisher: ApplicationEventPublisher) {

    fun execute(command: CreateHotelCommand){
        val savedHotel = repository.createHotel(Hotel(HotelId(command.id), HotelName(command.name)))
        eventPublisher.publishEvent( HotelCreated(savedHotel.hotelId));
    }
}

/*
fun getUserDataUseCase(
    hotelRepository: HotelRepository
): (command: CreateHotelCommand) ->  String {

    return

}*/

/*
fun createHotel(hotelRepository: HotelRepository, operation: (command: CreateHotelCommand) ->  Hotel): String {
    return operation
}

fun create(command: CreateHotelCommand):  Hotel {
    return Hotel()
}*/

/*
// regular function definition
fun add(a: Int, b: Int): Int{
    var sum = a + b
    return sum
}
// higher-order function definition
fun higherfunc(addfunc:(Int,Int)-> Int){
    // invoke regular function using local name
    var result = addfunc(3,6)
    print("The sum of two numbers is: $result")
}*/