package com.zzz1zzz.todolist_cmp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.zzz1zzz.todolist_cmp.domain.Task
import com.zzz1zzz.todolist_cmp.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TaskDetailsViewModel : ViewModel(), KoinComponent {

    private val taskRepository: TaskRepository by inject()

    private var _uiState = MutableStateFlow<TaskDetailsUiState>(TaskDetailsUiState.Loading)
    val uiState: StateFlow<TaskDetailsUiState>
        get() = _uiState

    private lateinit var taskJob: Job

    fun fetchTask(taskId: Long) {
        taskJob = viewModelScope.launch(Dispatchers.IO) {
            taskRepository.getTask(taskId).collect {
                Logger.d(TAG) {"fetchTask success. Task collected. task: $it"}
                _uiState.value = TaskDetailsUiState.Success(it)
            }.runCatching {
                _uiState.value = TaskDetailsUiState.Error
            }
        }
    }

    fun updateTask(taskId: Long, title: String, description: String?) {
        Logger.d(TAG) {"updateTask, taskId: $taskId, title:$title, description: $description" }
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(
                taskId = taskId,
                title = title,
                description = description,
            )
        }
    }

    fun deleteTask(taskId: Long) = viewModelScope.launch(Dispatchers.IO) {
        Logger.d(TAG) {"deleteTask, taskId: $taskId"}
        taskJob.cancel()
        taskRepository.deleteTask(taskId)
    }

    fun setIsCompleted(taskId: Long, isCompleted: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            Logger.d(TAG) {"setIsCompleted, taskId: $taskId, isCompleted: $isCompleted"}
            taskRepository.setCompleted(taskId, isCompleted)
        }

    companion object {
        private const val TAG = "TaskDetailsViewModel"
    }
}

sealed class TaskDetailsUiState {
    data object Loading : TaskDetailsUiState()
    data object Error : TaskDetailsUiState()
    class Success(val task: Task) : TaskDetailsUiState()
}
