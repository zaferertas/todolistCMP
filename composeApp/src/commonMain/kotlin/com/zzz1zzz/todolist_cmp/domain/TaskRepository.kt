package com.zzz1zzz.todolist_cmp.domain

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTask(taskId: Long): Flow<Task>
    fun getTasks(): Flow<List<Task>>
    suspend fun addTask(title: String, description: String?)
    suspend fun updateTask(taskId: Long, title: String, description: String?)
    suspend fun deleteTask(taskId: Long)
    suspend fun setCompleted(taskId: Long, isCompleted: Boolean)
}