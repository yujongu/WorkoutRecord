package com.example.workoutrecord.workout_info

class Workouts {
/*
    CHEST,
    BICEPS,
    BACK,
    TRICEPS,
    SHOULDER,
    LEGS
 */

    var workoutList: MutableMap<MuscleGroup, MutableList<Workout>> =
        mutableMapOf(
            MuscleGroup.CHEST to mutableListOf(
                Workout(name = "바벨 벤치브레스", reps = 0, weight = 0),
                Workout(name = "바벨 인클라인 벤치프레스", reps = 0, weight = 0),
                Workout(name = "덤벨 벤치프레스", reps = 0, weight = 0),
                Workout(name = "덤벨 인클라인 벤치프레스", reps = 0, weight = 0),
                Workout(name = "딥스", reps = 0, weight = 0),
                Workout(name = "덤벨 플라이", reps = 0, weight = 0),
                Workout(name = "크로스오버", reps = 0, weight = 0),
                Workout(name = "푸시업", reps = 0, weight = null),
                Workout(name = "체스트 인클라인", reps = 0, weight = 0),
                Workout(name = "펙토럴", reps = 0, weight = 0)),
            MuscleGroup.BICEPS to mutableListOf(
                Workout(name = "덤벨 컬", reps = 0, weight = 0),
                Workout(name = "해머 컬", reps = 0, weight = 0),
                Workout(name = "바벨 컬", reps = 0, weight = 0),
                Workout(name = "덤벨 프리쳐 컬", reps = 0, weight = 0),
                Workout(name = "바벨 프리쳐 컬", reps = 0, weight = 0),
                Workout(name = "원 암 케이블 컬", reps = 0, weight = 0),
                Workout(name = "원 암 컨센트레이션 컬", reps = 0, weight = 0),
                Workout(name = "로프 케이블 컬", reps = 0, weight = 0))

        )

    fun addToList(workoutType: MuscleGroup, workoutName: String, isWeight: Boolean){
        workoutList[workoutType]!!.add(Workout(workoutName, 0, if (isWeight) 0 else null))
    }



}