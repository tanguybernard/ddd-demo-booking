package com.example.training.preparation.wip.centric.application

import com.example.training.preparation.wip.centric.domain.Trainer
import com.example.training.preparation.wip.centric.domain.TrainerRepository
import org.springframework.stereotype.Service

@Service
class GetTrainer( val trainerRepository: TrainerRepository) {


    fun getTrainerById(id: String) : Trainer {
        return this.trainerRepository.findTrainerBy(id)
    }

}