package com.gajeks.electronicjournal.domain.models.selected_date

class SelectedDate {
    var weekObserver: WeekObserver? = null
    private var weekdayObservers: ArrayList<WeekdayObserver> = ArrayList()
    private var dateChangeObservers: ArrayList<DateChangeObserver> = ArrayList()

    fun addWeekdayObserver(weekdayObserver: WeekdayObserver) =
        weekdayObservers.add(weekdayObserver)

    fun removeWeekdayObserver(weekdayObserver: WeekdayObserver) =
        weekdayObservers.remove(weekdayObserver)

    fun addDateChangeObserver(dateChangeObserver: DateChangeObserver) =
        dateChangeObservers.add(dateChangeObserver)

    fun removeDateChangeObserver(dateChangeObserver: DateChangeObserver) =
        dateChangeObservers.remove(dateChangeObserver)

    var date: String? = null

    var selectedWeekday: Int? = null
        set(value) {
            if (value != field) {
                field = value
                dateChangeObservers.forEach { it.onDateChange() }
                weekdayObservers.forEach { it.onWeekdaySelection() }
            }
        }

    var selectedWeek: Int? = null
        set(value) {
            if (value != field) {
                field = value
                dateChangeObservers.forEach { it.onDateChange() }
                weekdayObservers.forEach { it.onWeekdaySelection() }
                weekObserver?.onWeekSelection()
                weekObserver = null
            }
        }
}