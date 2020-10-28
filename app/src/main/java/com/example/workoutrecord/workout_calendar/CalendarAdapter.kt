package com.example.workoutrecord.workout_calendar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.R
import com.example.workoutrecord.components.OnSwipeTouchListener

class CalendarAdapter(private val myDataset: List<MyDate>) :
    RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {

    val LONDONGREY = "#808e9b"
    val LYNXWHITE = "#f5f6fa"

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val container: ConstraintLayout = v.findViewById(R.id.constraintLayout)
        val myDate: TextView = v.findViewById<TextView>(R.id.day_item__date)

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
        val myData = myDataset[position]

        val date = holder.myDate
        val con = holder.container

//        con.setOnClickListener(View.OnClickListener {
//            for (i in 0 until myDataset.size){
//                myDataset[i].clicked = 0
//            }
//            myData.clicked = 1
//            notifyDataSetChanged()
//        })
//        con.setOnTouchListener(object : OnSwipeTouchListener(){
//            override fun onTouch() {
//                for (i in 0 until myDataset.size){
//                    myDataset[i].clicked = 0
//                }
//                myData.clicked = 1
//                notifyDataSetChanged()
//            }
//
//        })

        date.text = "${myData.day}"
        if (myData.month != myDataset[10].month){
            date.setTextColor(Color.parseColor(LONDONGREY))
        } else {
            date.setTextColor(Color.parseColor(LYNXWHITE))
        }

        if (myData.clicked == 1){
            con.setBackgroundResource(R.drawable.item_background_selected)
        } else {
            con.setBackgroundResource(R.drawable.item_background)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}