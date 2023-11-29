package com.example.training.preparation.domain.trainer

interface TrainerRepository {
    fun getTrainerBy(trainerId: TrainerId): Trainer
    fun add(trainer: Trainer)

}
