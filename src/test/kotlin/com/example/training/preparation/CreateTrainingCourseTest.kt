package com.example.training.preparation

import com.example.training.preparation.application.course.CreateTrainingCourse
import com.example.training.preparation.application.course.CreateTrainingCourseCommand
import com.example.training.preparation.domain.course.TrainingName
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateTrainingCourseTest {

    @Test
    fun createTraining() {

        //Given
        val trainingName = "The Grand Budapest Hotel"
        val repository = InMemoryTrainingRepository()
        val useCase = CreateTrainingCourse(repository) { }
        val command = CreateTrainingCourseCommand(
            trainingName,
            "",
            8,
            3
        )
        //When
        val idTrainingSaved = useCase.execute(command)

        //Then
        val trainingCourseSaved = repository.getTrainingCourseBy(idTrainingSaved)
        assertThat(trainingCourseSaved.trainingName).isEqualTo(TrainingName(trainingName))

    }
}