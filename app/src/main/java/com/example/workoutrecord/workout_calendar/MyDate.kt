package com.example.workoutrecord.workout_calendar

import com.example.workoutrecord.workout_info.MuscleGroup
import com.example.workoutrecord.workout_info.Workout
import com.example.workoutrecord.workout_info.Workouts

data class MyDate (val year: Int, val month: Int, val day: Int, var workedOut: Boolean){
    var muscleGroup: List<MuscleGroup> = emptyList<MuscleGroup>()
    var calories: Int = 0
    var clicked: Int = 0
    var workoutList: Map<MuscleGroup, List<Workout>> = mapOf()

}