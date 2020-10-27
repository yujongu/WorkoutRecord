package com.example.workoutrecord.workout_calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.R

class CalendarAdapter(private val myDataset: List<MyDate>) :
    RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val myDate = v.findViewById<TextView>(R.id.day_item__date)
        val myTime = v.findViewById<TextView>(R.id.day_item__workout_time)
        val myCalories = v.findViewById<TextView>(R.id.day_item__workout_calories)
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CalendarAdapter.MyViewHolder {
        // create a new view
        val context = parent.context

        val inflater = LayoutInflater.from(context)

        val calendarItemView = inflater.inflate(R.layout.calendar_day_item, parent, false)

        return MyViewHolder(calendarItemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val date = holder.myDate
        val time = holder.myTime
        val calories = holder.myCalories

        val myData = myDataset[position]

        date.text = (myData.day.toString())
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}