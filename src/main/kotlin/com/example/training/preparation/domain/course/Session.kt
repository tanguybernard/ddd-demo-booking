package com.example.training.preparation.domain.course

import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.shared.Entity
import java.time.LocalDate

class Session(
    val sessionId: SessionId,
    var trainerId: TrainerId,
    val startDate: LocalDate, val endDate: LocalDate

): Entity<SessionId>(sessionId)
