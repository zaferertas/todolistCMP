package com.zzz1zzz.todolist_cmp.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class DateTimeService {

    fun now(): Instant = Clock.System.now()

    fun Instant.toLocalDateTime(): LocalDateTime {
        return toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun LocalDateTime.toInstant(): Instant {
        return toInstant(TimeZone.currentSystemDefault())
    }
}