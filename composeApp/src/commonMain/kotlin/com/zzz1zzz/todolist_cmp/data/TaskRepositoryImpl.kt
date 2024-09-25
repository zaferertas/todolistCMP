package com.zzz1zzz.todolist_cmp.data

import com.zzz1zzz.todolist_cmp.domain.DateTimeService
import com.zzz1zzz.todolist_cmp.domain.TaskRepository
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDataSource: TaskDataSource,
    private val dateTimeService: DateTimeService,
) : TaskRepository {

    override fun getTask(taskId: Long) = taskDataSource.getTask(taskId).map {
        it.asDomain(dateTimeService)
    }

    override fun getTasks() = taskDataSource.getTasks().map {
        it.map { taskData ->
            taskData.asDomain(dateTimeService)
        }
    }

    override suspend fun addTask(title: String, description: String?) {
        taskDataSource.addTask(title, description, dateTimeService.now())
    }

    override suspend fun updateTask(
        taskId: Long,
        title: String,
        description: String?,
    ) {
        taskDataSource.updateTask(taskId, title, description)
    }

    override suspend fun deleteTask(taskId: Long) {
        taskDataSource.deleteTask(taskId)
    }

    override suspend fun setCompleted(taskId: Long, isCompleted: Boolean) {
        val completedAt = if (isCompleted) dateTimeService.now() else null
        taskDataSource.setCompleted(taskId, isCompleted, completedAt)
    }
}