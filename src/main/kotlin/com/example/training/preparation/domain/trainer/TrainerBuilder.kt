package com.example.training.preparation.domain.trainer

import com.example.training.preparation.domain.course.CourseId
import java.util.UUID

class TrainerBuilder {

    private var expertises : List<CourseId> = arrayListOf()
    private val id = TrainerId(UUID.randomUUID().toString())




    fun withExpertises(list: List<CourseId>) : TrainerBuilder {
        this.expertises = list
        return this
    }


    fun build(): Trainer{
        val trainer = Trainer(id)
        trainer.setExpertises(this.expertises)
        return trainer
    }
}