package com.zzz1zzz.todolist_cmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp

@Composable
fun TaskAddEditForm(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String?,
    onDescriptionChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = title,
            onValueChange = onTitleChanged,
            label = { Text(text = "Title") },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = description ?: "",
            onValueChange = onDescriptionChanged,
            label = { Text(text = "Description") },
            minLines = 3,
            maxLines = 5,
        )
        Spacer(Modifier.height(8.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            enabled = title.isNotEmpty(),
            onClick = onSaveClick
        ) {
            Text(text = "Save")
        }
    }
}