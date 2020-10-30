package com.example.workoutrecord.workout_calendar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.R
import com.example.workoutrecord.components.OnSwipeTouchListener
import com.jakewharton.threetenabp.AndroidThreeTen
import java.lang.IllegalStateException
import java.time.LocalDate
import java.time.LocalDateTime

class CalendarActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var myDataSet : MutableList<MyDate> = mutableListOf()
    var myCalendarList : List<MyDate> = listOf()


    private lateinit var prevMonthButton: ImageButton
    private lateinit var nextMonthButton: ImageButton
    private lateinit var currYrMonthTv: TextView
    private lateinit var todayTv: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        initInstances()
        eventListeners()
    }

    fun initInstances(){
        AndroidThreeTen.init(this);

        prevMonthButton = findViewById<ImageButton>(R.id.btnPrevMonth)
        nextMonthButton = findViewById<ImageButton>(R.id.btnNextMonth)
        currYrMonthTv = findViewById<TextView>(R.id.tvCurrYrMonth)
        todayTv = findViewById<TextView>(R.id.tvGetToday)

        generateRecyclerView()
    }



    @SuppressLint("ClickableViewAccessibility")
    fun eventListeners(){
        prevMonthButton.setOnClickListener(onClickListener)
        nextMonthButton.setOnClickListener(onClickListener)
        todayTv.setOnClickListener(onClickListener)

        recyclerView.setOnTouchListener(object : OnSwipeTouchListener(){
            override fun onSwipeLeft() {
                gotoNextMonth()
            }
            override fun onSwipeRight() {
                gotoPrevMonth()
            }
        })
    }



    val onClickListener : View.OnClickListener = View.OnClickListener {
        when (it.id){
            R.id.btnPrevMonth -> {
                gotoPrevMonth()
            }
            R.id.btnNextMonth -> {
                gotoNextMonth()
            }
            R.id.tvGetToday -> {
                gotoToday()
            }
            else -> throw IllegalStateException("Not sure what you clicked...")

        }
    }

    fun gotoPrevMonth(){
        if (myDataSet[10].month - 1 == 0){
            myDataSet = GenerateCalendar().generateCalendar(myDataSet, myDataSet[10].year - 1, 12)
        } else {
            myDataSet = GenerateCalendar().generateCalendar(myDataSet, myDataSet[10].year, myDataSet[10].month - 1)
        }
        myCalendarList = myDataSet
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"

        viewAdapter.notifyDataSetChanged()
    }

    fun gotoNextMonth(){
        if (myDataSet[10].month + 1 == 13){
            myDataSet = GenerateCalendar().generateCalendar(myDataSet, myDataSet[10].year + 1, 1)
        } else {
            myDataSet = GenerateCalendar().generateCalendar(myDataSet, myDataSet[10].year, myDataSet[10].month + 1)
        }
        myCalendarList = myDataSet
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"

        viewAdapter.notifyDataSetChanged()
    }

    fun gotoToday(){
        val today = org.threeten.bp.LocalDate.now()
        myDataSet = GenerateCalendar().generateCalendar(myDataSet, today.year, today.monthValue)

        myCalendarList = myDataSet
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"

        viewAdapter.notifyDataSetChanged()
    }
    fun generateRecyclerView(){
        val today = org.threeten.bp.LocalDateTime.now()

        myDataSet = GenerateCalendar().generateCalendar(myDataSet, today.year, today.monthValue)
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"
        myCalendarList = myDataSet

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