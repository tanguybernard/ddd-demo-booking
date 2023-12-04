package com.example.training.preparation.domain.trainer

import com.example.training.preparation.domain.course.CourseId
import com.example.training.shared.AggregateRoot

class Trainer(val trainerId: TrainerId) : AggregateRoot<TrainerId>(trainerId){

    private var expertises : Expertises = arrayListOf()


    fun setExpertises(expertises: Expertises) {
        this.expertises = expertises
    }

    fun getExpertises(): Expertises {
        return this.expertises
    }

    fun canAnimate(courseId: CourseId): Boolean {
        return expertises.contains(courseId)
    }

}

typealias Expertises = List<CourseId>