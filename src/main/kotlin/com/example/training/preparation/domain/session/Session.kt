package com.example.training.preparation.domain.session

import com.example.training.preparation.domain.TrainerId
import com.example.training.preparation.domain.course.TrainingId
import java.time.LocalDate

class Session(
    val sessionId: SessionId,
    val trainingId: TrainingId,
    val trainerId: TrainerId,
    val startDate: LocalDate, val endDate: LocalDate

) {


}
