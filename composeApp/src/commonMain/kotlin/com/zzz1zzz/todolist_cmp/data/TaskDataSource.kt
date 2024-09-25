package com.zzz1zzz.todolist_cmp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.zzz1zzz.todolist_cmp.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.datetime.Instant

class TaskDataSource(
    appDatabase: AppDatabase,
) {
    private val queries = appDatabase.taskQueries

    fun getTask(taskId: Long) = queries.selectById(taskId)
        .asFlow()
        .mapToOne(Dispatchers.IO)

    fun getTasks() = queries.selectAll()
        .asFlow()
        .mapToList(Dispatchers.IO)

    fun addTask(title: String, description: String?, createdAt: Instant) {
        queries.insert(title, description, createdAt)
    }

    fun deleteTask(taskId: Long) {
        queries.delete(taskId)
    }

    fun updateTask(taskId: Long, title: String, description: String?) {
        queries.update(
            id = taskId,
            title = title,
            description = description,
        )
    }

    fun setCompleted(taskId: Long, isCompleted: Boolean, completedAt: Instant?) {
        queries.setCompleted(id = taskId, isCompleted = isCompleted, completedAt = completedAt)
    }
}