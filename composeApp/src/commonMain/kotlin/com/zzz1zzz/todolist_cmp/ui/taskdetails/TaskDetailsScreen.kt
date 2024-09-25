package com.zzz1zzz.todolist_cmp.ui.taskdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.zzz1zzz.todolist_cmp.domain.Task
import com.zzz1zzz.todolist_cmp.ui.TaskAddEditForm
import com.zzz1zzz.todolist_cmp.ui.TaskDataPreviewProvider
import com.zzz1zzz.todolist_cmp.viewModel.TaskDetailsUiState
import com.zzz1zzz.todolist_cmp.viewModel.TaskDetailsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TaskDetailsRoute(
    taskId: Long,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    val viewModel: TaskDetailsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.fetchTask(taskId)
    }

    TaskDetailsScreen(
        uiState = uiState,
        modifier = modifier,
        onBackClick = onBackClick,
        onSaveClick = { title, description ->
            viewModel.updateTask(taskId, title, description)
            onBackClick()
        },
        onDeleteClick = {
            viewModel.deleteTask(it)
            onBackClick()
        },
        onSetCompletedClick = {
            viewModel.setIsCompleted(taskId, it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskDetailsScreen(
    uiState: TaskDetailsUiState,
    modifier: Modifier = Modifier,
    onSaveClick: (String, String?) -> Unit = { _, _ -> },
    onDeleteClick: (Long) -> Unit,
    onSetCompletedClick: (Boolean) -> Unit,
    onBackClick: () -> Unit,
) {

    var openDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Task Details")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            openDeleteDialog = true
                        }
                    ) {
//                        Icon(
//                            painterResource(R.drawable.baseline_delete_24),
//                            contentDescription = "Delete"
//                        )
                    }
                }
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            when (uiState) {
                TaskDetailsUiState.Error -> {}
                TaskDetailsUiState.Loading -> {}
                is TaskDetailsUiState.Success -> SuccessView(
                    task = uiState.task,
                    onSaveClick = onSaveClick,
                    onDeleteClick = {
                        openDeleteDialog = false
                        onDeleteClick(it)
                    },
                    openDeleteDialog = openDeleteDialog,
                    onDismissDeleteDialog = {
                        openDeleteDialog = false
                    },
                    onSetCompletedClick = onSetCompletedClick
                )
            }
        }
    }
}

@Composable
private fun SuccessView(
    task: Task,
    modifier: Modifier = Modifier,
    openDeleteDialog: Boolean = false,
    onDeleteClick: (Long) -> Unit,
    onSaveClick: (String, String?) -> Unit = { _, _ -> },
    onSetCompletedClick: (Boolean) -> Unit,
    onDismissDeleteDialog: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.padding(8.dp),
    ) {
        var title by remember { mutableStateOf(task.title) }
        var description by remember { mutableStateOf(task.description) }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (task.isCompleted) "Completed" else "Active",
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {
                    onSetCompletedClick(!task.isCompleted)
                }
            ) {
                Text(
                    text = if (task.isCompleted) "Set as active" else "Set completed",
                )
            }
        }

        Text(
            text = "Created at: ${task.createdAt}",
            style = MaterialTheme.typography.bodySmall,
        )
        task.completedAt?.let {
            Text(
                text = "Completed at: $it",
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TaskAddEditForm(
            title = title,
            onTitleChanged = { title = it },
            description = description,
            onDescriptionChanged = { description = it },
            onSaveClick = {
                keyboardController?.hide()
                onSaveClick(
                    title,
                    description,
                )
            },
        )
    }

    if (openDeleteDialog) {
        AlertDialog(
            onDismissRequest = onDismissDeleteDialog,
            title = { Text(text = "Delete Task?") },
            text = { Text(text = "Do you want to delete this task?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteClick(task.id)
                    }
                ) {
                    Text(
                        text = "Delete"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissDeleteDialog
                ) {
                    Text(
                        text = "Cancel"
//                        text = stringResource(id = android.R.string.cancel)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun TaskDetailsScreenPreview(@PreviewParameter(TaskDataPreviewProvider::class) tasks: List<Task>) {
    TaskDetailsScreen(
        uiState = TaskDetailsUiState.Success(tasks.first()),
        onBackClick = {},
        onDeleteClick = {},
        onSaveClick = { _, _ -> },
        onSetCompletedClick = { _ -> },
    )
}