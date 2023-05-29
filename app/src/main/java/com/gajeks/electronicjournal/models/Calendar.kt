package com.gajeks.electronicjournal.models

import java.util.Calendar

fun Calendar.setState() {
    this.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
    this.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1)

    if (Calendar.getInstance().get(Calendar.MONTH) < 6)
        this.set(Calendar.MONTH, Calendar.FEBRUARY)
    else
        this.set(Calendar.MONTH, Calendar.SEPTEMBER)
}