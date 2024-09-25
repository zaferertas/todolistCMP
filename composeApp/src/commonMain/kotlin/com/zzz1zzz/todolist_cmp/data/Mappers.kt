package com.zzz1zzz.todolist_cmp.data

import com.zzz1zzz.todolist_cmp.domain.DateTimeService
import com.zzz1zzz.todolist_cmp.domain.Task
import com.zzz1zzz.todolist_cmp.domain.formatDateTime
import com.zzz1zzz.todolistcmp.data.Taskdata

fun Taskdata.asDomain(dateTimeService: DateTimeService) = Task(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    createdAt = with(dateTimeService) {
        formatDateTime(createdAt.toLocalDateTime())
    },
    completedAt = with(dateTimeService) {
        completedAt?.let {
            formatDateTime(it.toLocalDateTime())
        }
    },
)
