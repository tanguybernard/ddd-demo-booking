package com.example.training.preparation

import com.example.training.preparation.application.course.UpdateTrainerOnSession
import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.preparation.domain.course.TrainingCourse
import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.CourseId
import com.example.training.preparation.domain.course.TrainingName
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.preparation.domain.course.Session
import com.example.training.preparation.domain.course.SessionId
import com.example.training.preparation.domain.trainer.Trainer
import com.example.training.preparation.domain.trainer.TrainerRepository
import com.example.training.preparation.domain.trainer.UpdateTrainerOnSessionDomainService
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainerRepository
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class UpdateTrainerOnSessionTest(

) {
    @Autowired
    private lateinit var courseMediator: CourseMediator

    private lateinit var trainerRepository: TrainerRepository

    private lateinit var trainingCourseRepository: TrainingCourseRepository
    private val trainerId = "#tbe"
    private lateinit var trainingId: String
    private lateinit var sessionId: String

    @BeforeEach
    fun setUp() {
        this.trainingCourseRepository = InMemoryTrainingRepository()
        this.trainerRepository = InMemoryTrainerRepository()

        this.trainingId = UUID.randomUUID().toString()

        val course = TrainingCourse(
                CourseId(this.trainingId),
                TrainingName("DDD Training"),
                3
        )


        val startDate = LocalDate.now().plusDays(3)
        val endDate = startDate.plusDays(3)
        sessionId = UUID.randomUUID().toString()
        course.addSession(
            Session(
                SessionId(sessionId),
                TrainerId(trainerId),
                startDate,
                endDate
            )
        )
        trainingCourseRepository.save(course)
    }


    @Test
    fun updateTrainerOnSession(){

        val domainService =  UpdateTrainerOnSessionDomainService(
        this.trainerRepository,
        this.trainingCourseRepository
        )

        val useCase = UpdateTrainerOnSession(domainService)

        val expectedTrainer = "#sdu"
        this.trainerRepository.add(Trainer(TrainerId(expectedTrainer)))

        useCase.execute(expectedTrainer, this.trainingId, this.sessionId)


        val trainerFound = trainingCourseRepository
            .getTrainingCourseBy(CourseId(trainingId))
            .getSessionBy(SessionId(this.sessionId)).trainerId

        //TODO update Test
        assertThat(trainerFound).isEqualTo(TrainerId("#sdu"))

    }
}
