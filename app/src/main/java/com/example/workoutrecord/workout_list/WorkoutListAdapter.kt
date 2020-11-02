package com.example.workoutrecord.workout_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.R
import com.example.workoutrecord.workout_calendar.CalendarAdapter
import com.example.workoutrecord.workout_calendar.MyDate
import com.example.workoutrecord.workout_info.MuscleGroup
import com.example.workoutrecord.workout_info.Workout
import kotlinx.android.synthetic.main.workout_list_item.view.*

class WorkoutListAdapter(private val myDataset: Map<MuscleGroup, List<Workout>>) :
    RecyclerView.Adapter<WorkoutListAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val workoutName: TextView = v.findViewById(R.id.workoutTitleTv)

    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WorkoutListAdapter.MyViewHolder {

        val context = parent.context

        val inflater = LayoutInflater.from(context)

        val workoutListAdapterView = inflater.inflate(R.layout.workout_list_item, parent, false)

        return MyViewHolder(workoutListAdapterView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val data = myDataset[position]
        val title = holder.workoutName

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}