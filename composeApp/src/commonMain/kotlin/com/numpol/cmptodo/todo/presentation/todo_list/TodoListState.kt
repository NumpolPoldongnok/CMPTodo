package com.numpol.cmptodo.todo.presentation.todo_list

import com.numpol.cmptodo.todo.domain.TodoItem

data class TodoListState(
    val todoList: List<TodoItem> = emptyList(),
    val completeList: List<TodoItem> = emptyList(),
    val selectedTabIndex: Int = 0
)
