package com.example.training.preparation.application.session

import java.time.LocalDate

class CreateSessionCommand(val startDate: LocalDate, val endDate: LocalDate, val trainingId: String, val trainerId: String) {

}
