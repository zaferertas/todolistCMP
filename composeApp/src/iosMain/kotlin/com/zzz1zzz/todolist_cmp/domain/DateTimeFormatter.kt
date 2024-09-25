package com.zzz1zzz.todolist_cmp.domain

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.datetime.LocalDateTime
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

@OptIn(ExperimentalForeignApi::class)
actual fun formatDateTime(localDateTime: LocalDateTime): String {
    val calendar = NSCalendar.currentCalendar
    val components = NSDateComponents()
    components.year = localDateTime.year.convert()
    components.month = localDateTime.monthNumber.convert()
    components.day = localDateTime.dayOfMonth.convert()
    components.hour = localDateTime.hour.convert()
    components.minute = localDateTime.minute.convert()
    components.second = localDateTime.second.convert()
    val date =  calendar.dateFromComponents(components)

    val formatter = NSDateFormatter().apply {
        dateFormat = "dd/MM/yyyy - HH:mm"
        locale = NSLocale.currentLocale
    }

    return date?.let {
        formatter.stringFromDate(date)
    } ?: ""
}