package com.zzz1zzz.todolist_cmp.ui

import com.zzz1zzz.todolist_cmp.domain.Task
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class TaskDataPreviewProvider : PreviewParameterProvider<List<Task>> {
    override val values: Sequence<List<Task>>
        get() = sequenceOf(
            listOf(
                Task(1, "Task 1", "Description text here", isCompleted = true, "12 Aug 2024 18:14","22 Aug 2024  2:16"),
                Task(2, "Completed Task Title", null, isCompleted = false, "6 Aug 2024  2:16",null),
                Task(3, "Task 3 Title", null, isCompleted = false, "6 Jul 2024 00:00",null),
                Task(4, "Another Task Title", "Description long long ", isCompleted = false, "12 Aug 2024 16:22",null),
            )
        )
}