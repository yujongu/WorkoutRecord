package com.example.workoutrecord.workout_list

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.R
import com.example.workoutrecord.workout_calendar.CalendarAdapter
import com.example.workoutrecord.workout_calendar.MyDate
import com.example.workoutrecord.workout_info.MuscleGroup
import com.example.workoutrecord.workout_info.Workout
import kotlinx.android.synthetic.main.workout_list_item.view.*

class WorkoutListAdapter(private val list: MutableList<Workout>) :
    RecyclerView.Adapter<WorkoutListAdapter.MyViewHolder>() {
    val SKYBLUE = "#70a1ff"
    val BLUENIGHT = "#353b48"
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val layout: LinearLayout = v.findViewById(R.id.itemLayout)
        val workoutName: TextView = v.findViewById(R.id.workoutTitleTv)
        val addSet: Button = v.findViewById(R.id.addSetBtn)

    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WorkoutListAdapter.MyViewHolder {

        val context = parent.context

        val inflater = LayoutInflater.from(context)

        val workoutListAdapterView = inflater.inflate(R.layout.workout_list_item, parent, false)

        return MyViewHolder(workoutListAdapterView)
    }

    private var clickedPos = -1;

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val workoutListData = list[position]

        val llContainer = holder.layout
        val workoutName = holder.workoutName
        val addWorkoutBtn = holder.addSet

        workoutName.text = workoutListData.name



        llContainer.setOnClickListener(View.OnClickListener {
            if (clickedPos == position){
                clickedPos = -1;
            } else {
                clickedPos = position
            }
            notifyDataSetChanged()
        })

        if (clickedPos >= 0 && clickedPos == position) {
            addWorkoutBtn.visibility = View.VISIBLE
            workoutName.setTextColor(Color.parseColor(SKYBLUE))

        } else {
            addWorkoutBtn.visibility = View.GONE
            workoutName.setTextColor(Color.parseColor(BLUENIGHT))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = list.size
}