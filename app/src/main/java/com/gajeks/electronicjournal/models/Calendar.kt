package com.gajeks.electronicjournal.models

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Calendar.setState() {
    this.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    this.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1)

    if (Calendar.getInstance().get(Calendar.MONTH) < 6)
        this.set(Calendar.MONTH, Calendar.FEBRUARY)
    else
        this.set(Calendar.MONTH, Calendar.SEPTEMBER)
}

@SuppressLint("SimpleDateFormat")
fun Calendar.getCurrentWeek(): Int? {
    val sdf = SimpleDateFormat("dd/MM/yyyy")

    val currentCalendar = Calendar.getInstance()

    val currentDate: Date = sdf.parse(
        "${currentCalendar.get(Calendar.DAY_OF_MONTH)}/" +
                "${currentCalendar.get(Calendar.MONTH) + 1}/" +
                "${currentCalendar.get(Calendar.YEAR)}"
    ) as Date

    val startDate: Date = sdf.parse(
        "${this.get(Calendar.DAY_OF_MONTH)}/" +
                "${this.get(Calendar.MONTH) + 1}/" +
                "${this.get(Calendar.YEAR)}"
    ) as Date

    val diff = kotlin.math.abs(currentDate.time - startDate.time)
    val days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

    return if (days <= 118) {
        (days / 7).toInt()
    } else {
        null
    }
}

fun Calendar.getMonth(): String = when (this.get(Calendar.MONTH)) {
    0 -> "Январь"
    1 -> "Февраль"
    2 -> "Март"
    3 -> "Апрель"
    4 -> "Май"
    5 -> "Июнь"
    6 -> "Июль"
    7 -> "Август"
    8 -> "Сентябрь"
    9 -> "Октябрь"
    10 -> "Ноябрь"
    11 -> "Декабрь"
    else -> throw IllegalArgumentException()
}