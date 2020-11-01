package com.example.workoutrecord.workout_info

data class WorkoutDetails (val year: Int, val month: Int, val day: Int, var workedOut: Boolean){
    var muscleGroup: List<MuscleGroup> = emptyList<MuscleGroup>()
    var calories: Int = 0
    var clicked: Int = 0
}