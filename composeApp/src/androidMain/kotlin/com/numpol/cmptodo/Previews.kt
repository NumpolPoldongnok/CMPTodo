package com.numpol.cmptodo


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.numpol.cmptodo.todo.domain.TodoItem
import com.numpol.cmptodo.todo.presentation.todo_detail.TodoDetailScreen
import com.numpol.cmptodo.todo.presentation.todo_list.TodoListScreen
import com.numpol.cmptodo.todo.presentation.todo_list.TodoListState

val todoItems: List<TodoItem> = (0..10).map {
    val value = it.toString()
    TodoItem(
        id = value,
        title = "Todo $value",
        detail = "Detail $value",
        date = "Date $value",
        finished = false
    )
}

@Preview
@Composable
fun PreviewTodoListScreen() {
    MaterialTheme {
        Surface {
            TodoListScreen(
                state = TodoListState(todoList = todoItems)
            ) { }
        }
    }
}

@Preview
@Composable
fun PreviewTodoDetailScreen() {
    MaterialTheme {
        Surface {
            TodoDetailScreen(todoItem = todoItems[0]) { }
        }
    }
}