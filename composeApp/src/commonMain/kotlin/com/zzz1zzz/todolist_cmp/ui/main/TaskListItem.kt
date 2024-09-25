package com.zzz1zzz.todolist_cmp.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zzz1zzz.todolist_cmp.ui.TaskDataPreviewProvider
import com.zzz1zzz.todolist_cmp.domain.Task
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
fun TaskListItem(
    modifier: Modifier = Modifier,
    task: Task,
    onItemClick: (Long) -> Unit,
    onCompleteTaskClick: (Long, Boolean) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(4.dp)
            .clickable {
                onItemClick(task.id)
            },
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { isChecked ->
                    onCompleteTaskClick(task.id, isChecked)
                },
            )
            Text(text = task.title)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Navigate to task details"
            )
        }
    }
}

@Preview
@Composable
private fun TaskListItemPreview(@PreviewParameter(TaskDataPreviewProvider::class) tasks: List<Task>) {
    TaskListItem(
        task = tasks.first(),
        onItemClick = {},
        onCompleteTaskClick = {_, _ ->}
    )
}
