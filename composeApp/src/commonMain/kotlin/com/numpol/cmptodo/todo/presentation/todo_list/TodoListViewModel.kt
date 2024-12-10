package com.numpol.cmptodo.todo.presentation.todo_list

import androidx.lifecycle.ViewModel
import com.numpol.cmptodo.core.util.DateUtils
import com.numpol.cmptodo.todo.domain.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TodoListViewModel : ViewModel() {
    private val _state = MutableStateFlow(TodoListState())
    val state = _state.asStateFlow()

    init {
        val todoList = listOf(
            TodoItem("1", "Task 1", "Details for task 1", "2024-12-01", finished = false),
            TodoItem("2", "Task 2", "Details for task 2", "2024-12-02", finished = false),
            TodoItem("3", "Task 3", "Details for task 3", "2024-11-30", finished = false)
        )
        _state.update { it.copy(todoList = todoList) }
    }

    fun onAction(action: TodoListAction) {
        when(action) {
            is TodoListAction.OnTabSelected -> onTabSelected(action.index)
            is TodoListAction.OnCheckedChange -> onCheckedChange(action.todoItem)
            is TodoListAction.OnAddTodoClick -> TODO()
            is TodoListAction.OnTodoClick -> TODO()
        }
    }

    private fun onCheckedChange(todoItem: TodoItem) {
        _state.update { currentState ->
            if (todoItem.finished) {
                moveItem(
                    sourceList = currentState.completeList,
                    targetList = currentState.todoList,
                    item = todoItem,
                    finished = false
                )
            } else {
                moveItem(
                    sourceList = currentState.todoList,
                    targetList = currentState.completeList,
                    item = todoItem,
                    finished = true
                )
            }
        }
    }

    private fun onTabSelected(index: Int) {
        _state.update { it.copy(selectedTabIndex = index) }
    }

    private fun moveItem(
        sourceList: List<TodoItem>,
        targetList: List<TodoItem>,
        item: TodoItem,
        finished: Boolean
    ): TodoListState {
        val updatedSourceList = sourceList.filter { it.id != item.id }
        val updatedTargetList = targetList + item.copy(finished = finished)
        return _state.value.copy(
            todoList = if (finished) updatedSourceList else updatedTargetList,
            completeList = if (finished) updatedTargetList else updatedSourceList
        )
    }
}
