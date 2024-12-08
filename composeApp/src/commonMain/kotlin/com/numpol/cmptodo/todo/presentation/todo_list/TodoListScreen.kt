package com.numpol.cmptodo.todo.presentation.todo_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.numpol.cmptodo.todo.domain.TodoItem

@Composable
fun TodoListScreen(todoItems: List<TodoItem>, onItemClicked: (TodoItem) -> Unit) {
    LazyColumn(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(todoItems, key = { it.id }) {
            Button(
                onClick = {
                    onItemClicked(it)
                }) {
                Text(it.title)
            }
        }
    }
}