package com.example.workoutrecord.workout_calendar

import com.example.workoutrecord.workout_info.MuscleGroup

data class MyDate (val year: Int, val month: Int, val day: Int, var workedOut: Boolean){
    var muscleGroup: List<MuscleGroup> = emptyList<MuscleGroup>()
    var calories: Int = 0
    var clicked: Int = 0
}