package com.example.workoutrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.workoutrecord.workout_info.MuscleGroup
import com.example.workoutrecord.workout_info.Workouts
import com.example.workoutrecord.workout_list_pager.WorkoutListPagerAdapter

class ChooseWorkoutActivity : AppCompatActivity() {
    private lateinit var vPagerView: ViewPager
    private lateinit var workouts: Workouts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_workout)

        initInstances()
        eventListeners()


    }

    fun initInstances(){
        workouts = Workouts()
        vPagerView = findViewById(R.id.mViewPager)
    }

    fun eventListeners(){
        val pagerAdapter = WorkoutListPagerAdapter(workouts.workoutList)
        vPagerView.adapter = pagerAdapter

    }
}