package com.example.training.preparation;

import com.example.training.preparation.application.course.RemoveCourse
import com.example.training.preparation.domain.CourseRemovedDomainEvent
import com.example.training.preparation.domain.TrainerId
import com.example.training.preparation.domain.course.TrainingCourse
import com.example.training.preparation.domain.course.TrainingCourseRepository
import com.example.training.preparation.domain.course.TrainingId
import com.example.training.preparation.domain.course.TrainingName
import com.example.training.preparation.domain.mediatorPattern.CourseMediator
import com.example.training.preparation.domain.session.Session
import com.example.training.preparation.domain.session.SessionId
import com.example.training.preparation.domain.session.SessionRepository
import com.example.training.preparation.infrastructure.stubs.InMemorySessionRepository
import com.example.training.preparation.infrastructure.stubs.InMemoryTrainingRepository
import com.example.training.preparation.infrastructure.stubs.TrainingCourseDoesNotExist
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class RemoveTrainingCourseTest(

) {
    @Autowired
    private lateinit var courseMediator: CourseMediator<CourseRemovedDomainEvent>

    private lateinit var trainingCourseRepository: TrainingCourseRepository
    private lateinit var sessionRepository: SessionRepository
    private val trainerId = "#tbe"
    private lateinit var trainingId: String
    private lateinit var sessionId: SessionId

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
        val startDate = LocalDate.now().plusDays(3)
        val endDate = startDate.plusDays(3)
        sessionId = this.sessionRepository.nextId()
        this.sessionRepository.create(
            Session(sessionId,
                TrainingId(this.trainingId),
                TrainerId(trainerId),
                startDate,
                endDate
            )
        )
    }


    @Test
    fun publishTrainingSession(){

        val useCase = RemoveCourse(this.trainingCourseRepository, courseMediator)

        useCase.execute(this.trainingId)

        //TODO update Test
        assertThat(true).isTrue()

    }
}
