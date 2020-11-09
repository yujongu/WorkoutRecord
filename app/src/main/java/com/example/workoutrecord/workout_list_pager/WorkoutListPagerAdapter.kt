package com.example.workoutrecord.workout_list_pager

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.workoutrecord.R
import com.example.workoutrecord.components.OnSwipeTouchListener
import com.example.workoutrecord.workout_info.MuscleGroup
import com.example.workoutrecord.workout_info.Workout
import com.example.workoutrecord.workout_list.WorkoutListAdapter
import kotlinx.android.synthetic.main.workout_list_page.view.*

class WorkoutListPagerAdapter(private val list: MutableMap<MuscleGroup, MutableList<Workout>>) : PagerAdapter() {

    private var workListAdapter: RecyclerView.Adapter<*>? = null
    private var workoutListManager: RecyclerView.LayoutManager? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)

        val view = inflater.inflate(R.layout.workout_list_page, container, false)
        var keyName = when(position) {
            0 -> MuscleGroup.CHEST
            else -> MuscleGroup.BICEPS
        }

        workoutListManager = LinearLayoutManager(container.context)
        workListAdapter = WorkoutListAdapter(list[keyName]!!)

        view.muscleGroupTv.text = keyName.name

        view.workout_list_page_recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = workoutListManager
            // specify an viewAdapter (see also next example)
            adapter = workListAdapter
        }

        container.addView(view, position)

        return view
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

}