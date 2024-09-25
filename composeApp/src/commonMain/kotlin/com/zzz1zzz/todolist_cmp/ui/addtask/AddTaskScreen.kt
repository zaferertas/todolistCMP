package com.zzz1zzz.todolist_cmp.ui.addtask

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.zzz1zzz.todolist_cmp.ui.TaskAddEditForm
import com.zzz1zzz.todolist_cmp.viewModel.AddTaskViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AddTaskRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onDone: () -> Unit = {},
) {
    val viewModel: AddTaskViewModel = koinViewModel()

    AddTaskScreen(
        modifier = modifier,
        onSaveClick = { title: String, description: String? ->
            viewModel.addTask(title, description)
            onDone()
        },
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskScreen(
    modifier: Modifier = Modifier,
    onSaveClick: (String, String?) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {},
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Task")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            onBackClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf<String?>(null) }

            TaskAddEditForm(
                modifier = Modifier.padding(8.dp),
                title = title,
                onTitleChanged = { title = it },
                description = description,
                onDescriptionChanged = { description = it },
                onSaveClick = {
                    keyboardController?.hide()
                    onSaveClick(title, description)
                },
                focusRequester = focusRequester,
            )
        }
    }
}

@Preview
@Composable
private fun AddTaskScreenPreview() {
    AddTaskScreen()
}