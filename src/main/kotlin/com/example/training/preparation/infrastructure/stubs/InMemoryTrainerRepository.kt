package com.example.training.preparation.infrastructure.stubs

import com.example.training.preparation.domain.trainer.Trainer
import com.example.training.preparation.domain.trainer.TrainerId
import com.example.training.preparation.domain.trainer.TrainerNotFound
import com.example.training.preparation.domain.trainer.TrainerRepository

class InMemoryTrainerRepository : TrainerRepository {

    private var list : HashMap<TrainerId, Trainer> = hashMapOf()

    override fun getTrainerBy(trainerId: TrainerId): Trainer {
        return list.getOrElse(trainerId) {throw TrainerNotFound(trainerId) }
    }

    override fun add(trainer: Trainer) {
        list[trainer.trainerId] = trainer
    }
}