package com.numpol.cmptodo.todo.presentation.todo_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.numpol.cmptodo.todo.domain.TodoItem

@Composable
fun TodoDetailScreen(todoItem: TodoItem, onBackClicked: () -> Unit) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column {
            Text(todoItem.toString())
            Button(onClick = onBackClicked) {
                Text("Back")
            }
        }
    }
}