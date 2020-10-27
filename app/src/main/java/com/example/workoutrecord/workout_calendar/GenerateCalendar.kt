package com.example.workoutrecord.workout_calendar

import android.util.Log
import com.jakewharton.threetenabp.AndroidThreeTen
import java.time.LocalDateTime
import java.time.Year
import java.util.*

class GenerateCalendar {

    val TAG = "generateCalendarTag"

    fun generateCalendar(calendar: MutableList<MyDate>) : MutableList<MyDate> {
        val currentDate = org.threeten.bp.LocalDateTime.now()
        val yr = org.threeten.bp.Year.now()
        val startMonth = org.threeten.bp.LocalDateTime.of(currentDate.year, currentDate.monthValue, 1, 0, 0)
        val prevMonth = currentDate.minusMonths(1)
        val nextMonth = currentDate.plusMonths(1)

        var count = 0

        if(startMonth.dayOfWeek.value != 7){
            for (i in 0 until startMonth.dayOfWeek.value){
                calendar.add(
                    MyDate(
                        prevMonth.year,
                        prevMonth.monthValue,
                        prevMonth.month.length(yr.isLeap) - startMonth.dayOfWeek.value + i + 1,
                        false
                    )
                )
                count++
            }
        } else {
            calendar.add(
                MyDate(
                    prevMonth.year,
                    prevMonth.monthValue,
                    prevMonth.month.length(yr.isLeap),
                    false
                )
            )
            count++
        }

        for (i in 1..currentDate.month.length(yr.isLeap)){
            calendar.add(
                MyDate(
                    currentDate.year,
                    currentDate.monthValue,
                    i,
                    false
                )
            )
            count++
        }

        for (i in count until 42){
            calendar.add(
                MyDate(
                    nextMonth.year,
                    nextMonth.monthValue,
                    i - count + 1,
                    false
                )
            )
        }

        return calendar
    }

}