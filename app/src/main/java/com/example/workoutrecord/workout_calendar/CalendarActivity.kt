package com.example.workoutrecord.workout_calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.R
import com.jakewharton.threetenabp.AndroidThreeTen
import java.time.LocalDateTime

class CalendarActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        AndroidThreeTen.init(this);

        var myDataSet : MutableList<MyDate> = mutableListOf()
        myDataSet = GenerateCalendar().generateCalendar(myDataSet)

        var myCalendarList : List<MyDate> = myDataSet

        viewManager = GridLayoutManager(this, 7)
        viewAdapter = CalendarAdapter(myCalendarList)

        recyclerView = findViewById<RecyclerView>(R.id.calendarRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }
}