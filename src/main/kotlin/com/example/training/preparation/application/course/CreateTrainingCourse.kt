package com.example.training.preparation.application.course

import com.example.training.preparation.domain.course.*
import org.springframework.context.ApplicationEventPublisher


class CreateTrainingCourse(private val repository: TrainingCourseRepository, private val eventPublisher: ApplicationEventPublisher) {

    fun execute(command: CreateTrainingCourseCommand): CourseId {

        val trainingId = repository.nextId()
        val savedTrainingCourse = repository.createTrainingCourse(TrainingCourse(trainingId, TrainingName(command.name), command.duration))
        eventPublisher.publishEvent( TrainingCourseCreated(savedTrainingCourse.courseId))
        return trainingId
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