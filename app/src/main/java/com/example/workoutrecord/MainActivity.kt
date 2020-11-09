package com.example.workoutrecord

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutrecord.components.OnSwipeTouchListener
import com.example.workoutrecord.workout_calendar.CalendarAdapter
import com.example.workoutrecord.workout_calendar.GenerateCalendar
import com.example.workoutrecord.workout_calendar.MyDate
import com.example.workoutrecord.workout_info.MuscleGroup
import com.example.workoutrecord.workout_list.WorkoutListAdapter
import com.jakewharton.threetenabp.AndroidThreeTen
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    private lateinit var calRecyclerView: RecyclerView
    private lateinit var calViewAdapter: RecyclerView.Adapter<*>
    private lateinit var calViewManager: RecyclerView.LayoutManager
    var myDataSet: MutableList<MyDate> = mutableListOf()
    var myCalendarList: List<MyDate> = listOf()

    private lateinit var workListRecyclerView: RecyclerView
    private lateinit var workListViewAdapter: RecyclerView.Adapter<*>
    private lateinit var workListViewManager: RecyclerView.LayoutManager

    private lateinit var prevMonthButton: ImageButton
    private lateinit var nextMonthButton: ImageButton
    private lateinit var currYrMonthTv: TextView
    private lateinit var todayTv: TextView
    private lateinit var addWorkout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        initInstances()
        eventListeners()
    }

    fun initInstances() {
        AndroidThreeTen.init(this);

        prevMonthButton = findViewById(R.id.btnPrevMonth)
        nextMonthButton = findViewById(R.id.btnNextMonth)
        currYrMonthTv = findViewById(R.id.tvCurrYrMonth)
        todayTv = findViewById(R.id.tvGetToday)
        addWorkout = findViewById(R.id.addWorkoutBtn)

        generateCalendarRecyclerView()
//        generateWorkoutListRecyclerView()
    }

    @SuppressLint("ClickableViewAccessibility")
    fun eventListeners() {
        prevMonthButton.setOnClickListener(onClickListener)
        nextMonthButton.setOnClickListener(onClickListener)
        todayTv.setOnClickListener(onClickListener)

        addWorkout.setOnClickListener(onClickListener)

        //swipe screen for prev or next month.
        calRecyclerView.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                gotoNextMonth()
            }
            override fun onSwipeRight() {
                gotoPrevMonth()
            }
        })
    }

    val onClickListener: View.OnClickListener = View.OnClickListener {
        when (it.id) {
            R.id.btnPrevMonth -> {
                gotoPrevMonth()
            }
            R.id.btnNextMonth -> {
                gotoNextMonth()
            }
            R.id.tvGetToday -> {
                gotoToday()
            }
            R.id.addWorkoutBtn -> {
                redirectToChooseWorkout()
            }
            else -> throw IllegalStateException("Not sure what you clicked...")

        }
    }

    fun gotoPrevMonth() {
        if (myDataSet[10].month - 1 == 0) {
            myDataSet = GenerateCalendar().generateCalendar(myDataSet, myDataSet[10].year - 1, 12)
        } else {
            myDataSet = GenerateCalendar().generateCalendar(
                myDataSet,
                myDataSet[10].year,
                myDataSet[10].month - 1
            )
        }
        myCalendarList = myDataSet
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"

        calViewAdapter.notifyDataSetChanged()
    }

    fun gotoNextMonth() {
        if (myDataSet[10].month + 1 == 13) {
            myDataSet = GenerateCalendar().generateCalendar(myDataSet, myDataSet[10].year + 1, 1)
        } else {
            myDataSet = GenerateCalendar().generateCalendar(
                myDataSet,
                myDataSet[10].year,
                myDataSet[10].month + 1
            )
        }
        myCalendarList = myDataSet
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"

        calViewAdapter.notifyDataSetChanged()
    }

    fun gotoToday() {
        val today = org.threeten.bp.LocalDate.now()
        myDataSet = GenerateCalendar().generateCalendar(myDataSet, today.year, today.monthValue)

        myCalendarList = myDataSet
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"

        calViewAdapter.notifyDataSetChanged()
    }

    private fun generateCalendarRecyclerView() {
        val today = org.threeten.bp.LocalDateTime.now()

        myDataSet = GenerateCalendar().generateCalendar(myDataSet, today.year, today.monthValue)
        currYrMonthTv.text = "${myDataSet[10].year}.${myDataSet[10].month}"
        myCalendarList = myDataSet

        calViewManager = GridLayoutManager(this, 7)
        calViewAdapter = CalendarAdapter(myCalendarList)

        calRecyclerView = findViewById<RecyclerView>(R.id.calendarRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = calViewManager
            // specify an viewAdapter (see also next example)
            adapter = calViewAdapter
        }
    }

    private fun redirectToChooseWorkout(){
        val intent = Intent(this@MainActivity, ChooseWorkoutActivity::class.java )
        startActivity(intent)
    }




}