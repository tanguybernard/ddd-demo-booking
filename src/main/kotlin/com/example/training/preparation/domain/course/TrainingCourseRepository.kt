package com.example.training.preparation.domain.course

interface TrainingCourseRepository {

    fun getTrainingCourseBy(id: TrainingId) : TrainingCourse
    fun createTrainingCourse(trainingCourse: TrainingCourse): TrainingCourse

    fun update(trainingCourse: TrainingCourse)
    fun nextId(): TrainingId
    fun save(course: TrainingCourse)

}
