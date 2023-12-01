package com.example.training.preparation.domain.trainer

import com.example.training.preparation.domain.course.CourseId
import com.example.training.shared.AggregateRoot

class Trainer(val trainerId: TrainerId) : AggregateRoot<TrainerId>(trainerId){

    private var expertises : List<CourseId> = arrayListOf()


    fun setExpertises(expertises: List<CourseId>) {
        this.expertises = expertises
    }


    companion object {

        class ComputerBuilder {

            // required parameters
            val experiences: List<String> = TODO();


            fun withExperiences() {

            }

        }
    }


}
