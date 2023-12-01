package com.example.training.preparation.domain.course

interface TrainingCourseRepository {

    fun getTrainingCourseBy(id: CourseId) : TrainingCourse
    fun createTrainingCourse(trainingCourse: TrainingCourse): TrainingCourse

    fun update(trainingCourse: TrainingCourse)
    fun nextId(): CourseId
    fun save(course: TrainingCourse)

}
