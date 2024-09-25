package com.zzz1zzz.todolist_cmp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.zzz1zzz.todolist_cmp.domain.Task
import com.zzz1zzz.todolist_cmp.domain.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val taskRepository: TaskRepository by inject()

    val uiState: StateFlow<MainUiState> = taskRepository.getTasks()
        .map { tasks ->
            Logger.d(TAG) {"uiState success, map tasks count: ${tasks.count()}"}
            val (activeTasks, completedTasks) = tasks.partition { !it.isCompleted }
            MainUiState.Success(activeTasks, completedTasks)
        }.catch {
            MainUiState.Error
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            MainUiState.Loading
        )

    fun setIsCompleted(taskId: Long, isCompleted: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            Logger.d(TAG) {"setIsCompleted, taskId: $taskId, isCompleted: $isCompleted"}
            taskRepository.setCompleted(taskId, isCompleted)
        }

    companion object {
        private const val TAG = "MainViewModel"
    }
}

sealed class MainUiState {
    data object Loading : MainUiState()
    data object Error : MainUiState()
    class Success(
        val activeTasks: List<Task>,
        val completedTasks: List<Task>,
    ) : MainUiState()
}

