package com.example.training.preparation

import com.example.training.preparation.application.session.CreateSessionCommand
import com.example.training.preparation.application.session.CreateTrainingSession
import com.example.training.preparation.application.session.SessionPeriodIsIncorrectToTheDurationOfACourse
import com.example.training.preparation.application.session.TrainingSessionDomainService
import com.example.training.preparation.domain.course.TrainingCourse
import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.TrainingId
import com.example.training.preparation.domain.course.TrainingName
import com.example.training.preparation.domain.session.SessionRepository
import com.example.training.preparation.infrastructure.stubs.InMemorySessionRepository
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainingRepository
import com.example.training.preparation.infrastructure.stubs.TrainingCourseDoesNotExist
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.util.*


class CreateTrainingSessionTest {


    private lateinit var trainingCourseRepository: TrainingCourseRepository
    private lateinit var sessionRepository: SessionRepository
    private val trainerId = "#tbe"
    private lateinit var trainingId: String

    @BeforeEach
    fun setUp() {
        this.trainingCourseRepository = InMemoryTrainingRepository()

        this.trainingId = UUID.randomUUID().toString()
        trainingCourseRepository.createTrainingCourse(
            TrainingCourse(
                TrainingId(this.trainingId),
                TrainingName("DDD Training"),
                3
            ))
        this.sessionRepository = InMemorySessionRepository()
    }


    @Test
    fun createTrainingSession(){

        val useCase = CreateTrainingSession(
            trainingSessionDomainService = TrainingSessionDomainService(sessionRepository, trainingCourseRepository)
        )
        val startDate = LocalDate.now().plusDays(3)
        val endDate = startDate.plusDays(3)
        val sessionId = useCase.execute(CreateSessionCommand(startDate, endDate, this.trainingId, trainerId))

        assertThat(sessionRepository.getSessionBy(sessionId).trainingId).isEqualTo(TrainingId(this.trainingId))

    }


    @Test
    fun `create a session where period does not match with the duration of the course`(){

        val useCase = CreateTrainingSession(
            trainingSessionDomainService = TrainingSessionDomainService(sessionRepository, trainingCourseRepository)
        )
        val startDate = LocalDate.now().plusDays(3)
        val endDate = startDate.plusDays(2)

        assertThrows<SessionPeriodIsIncorrectToTheDurationOfACourse> {
            useCase.execute(CreateSessionCommand(startDate, endDate, this.trainingId, trainerId))

        }



    }

    @Test
    fun `create a session for Training course that no longer exists`(){
        val useCase = CreateTrainingSession(
            trainingSessionDomainService = TrainingSessionDomainService(sessionRepository, trainingCourseRepository)
        )
        val startDate = LocalDate.now().plusDays(3)
        val endDate = startDate.plusDays(2)

        assertThrows<TrainingCourseDoesNotExist> {
            useCase.execute(CreateSessionCommand(startDate, endDate, "IdDoesNotExist", trainerId))

        }
    }
}
