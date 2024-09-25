package com.zzz1zzz.todolist_cmp.domain

data class Task(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val createdAt: String,
    val completedAt: String?,
)
