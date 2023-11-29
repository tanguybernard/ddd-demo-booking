package com.example.training.preparation.domain.trainer

import com.example.training.shared.AggregateRoot

class Trainer(val trainerId: TrainerId) : AggregateRoot<TrainerId>(trainerId){

}
