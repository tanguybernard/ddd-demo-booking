package com.example.training.preparation.application.course

data class CreateTrainingCourseCommand(
    val name: String,
    val description: String,
    val maximumNumberOfPlacesAvailable: Int,
    val duration: Int
) {

}
