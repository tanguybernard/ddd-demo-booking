package com.example.training.preparation.application.course

import java.time.LocalDate

class CreateSessionCommand(val startDate: LocalDate, val endDate: LocalDate, val trainingId: String, val trainerId: String) {

}
