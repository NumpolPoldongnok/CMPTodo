package com.numpol.cmptodo.todo.presentation.todo_list

import com.numpol.cmptodo.todo.domain.TodoItem

sealed interface TodoListAction {
    data class OnTodoClick(val todoItem: TodoItem): TodoListAction
    data class OnCheckedChange(val todoItem: TodoItem): TodoListAction
    data class OnAddTodoClick(val todoItem: TodoItem): TodoListAction
    data class OnTabSelected(val index: Int): TodoListAction
}