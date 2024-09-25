package com.zzz1zzz.todolist_cmp.domain

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


actual fun formatDateTime(localDateTime: LocalDateTime): String {
    return DateTimeFormatter
        .ofPattern("dd/MM/yyyy - HH:mm", Locale.getDefault())
        .format(localDateTime.toJavaLocalDateTime())
}