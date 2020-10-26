package com.example.workoutrecord.workout_calendar

import android.util.Log
import com.jakewharton.threetenabp.AndroidThreeTen
import java.time.LocalDateTime
import java.time.Year
import java.util.*

class GenerateCalendar {

    val TAG = "---GenerateCalendarTag"

    fun generateCalendar(calendar: MutableList<MyDate>) : MutableList<MyDate> {
        val currentDate = org.threeten.bp.LocalDateTime.now()
        var date = MyDate(currentDate.year, currentDate.monthValue, 1, false)

        Log.i(TAG, "${date.year}, ${date.month}, ${currentDate.dayOfWeek.value}")

        return calendar
    }

}