package com.example.workoutrecord.workout_calendar

class GenerateCalendar {

    val TAG = "generateCalendarTag"

    fun generateCalendar(calendar: MutableList<MyDate>, year: Int, month: Int) : MutableList<MyDate> {
        calendar.clear()
        val today = org.threeten.bp.LocalDate.now()

        val yr = org.threeten.bp.Year.of(year)
        val startMonth = org.threeten.bp.LocalDate.of(year, month, 1)
        val prevMonth = startMonth.minusMonths(1)
        val nextMonth = startMonth.plusMonths(1)

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

        for (i in 1..startMonth.month.length(yr.isLeap)){
            calendar.add(
                MyDate(
                    startMonth.year,
                    startMonth.monthValue,
                    i,
                    false,
                )

            )
            if ((calendar[count].year == today.year) && (calendar[count].month == today.monthValue) && (calendar[count].day == today.dayOfMonth)) {
                calendar[count].clicked = 1
            }
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