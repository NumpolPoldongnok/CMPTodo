package com.numpol.cmptodo.todo.presentation.todo_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cmptodo.composeapp.generated.resources.Res
import cmptodo.composeapp.generated.resources.no_todo_list
import com.numpol.cmptodo.todo.domain.TodoItem
import com.numpol.cmptodo.todo.presentation.todo_list.components.TodoListView
import org.jetbrains.compose.resources.stringResource

@Composable
fun TodoListScreenRoot(
    onTodoClick: (TodoItem) -> Unit,
) {
    val todoList = remember {
        mutableStateListOf(
            TodoItem("1", "Task 1", "Details for task 1", "2024-12-01", finished = false),
            TodoItem("2", "Task 2", "Details for task 2", "2024-12-02", finished = false),
            TodoItem("3", "Task 3", "Details for task 3", "2024-11-30", finished = true)
        )
    }
    TodoListScreen(
        todoList = todoList,
        onTodoClick = onTodoClick,
        onCheckedChange = { todoItem ->
        }
    )
}

@Composable
fun TodoListScreen(
    todoList: List<TodoItem>,
    onTodoClick: (TodoItem) -> Unit,
    onCheckedChange: (TodoItem) -> Unit,
) {
    val todoListState = rememberLazyListState()

    if (todoList.isEmpty()) {
        Text(
            text = stringResource(Res.string.no_todo_list),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
        )
    } else {
        TodoListView(
            todoList = todoList,
            onTodoClick = onTodoClick,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.fillMaxSize(),
            scrollState = todoListState
        )
    }
}
