package com.numpol.cmptodo.todo.presentation.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.numpol.cmptodo.todo.domain.TodoItem
import com.numpol.cmptodo.todo.domain.TodoRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    private var observeTodoJob: Job? = null

    private val _state = MutableStateFlow(TodoListState())
    val state = _state.asStateFlow()

    init {
        observeTodoList()
        //populateSampleData() // Comment this code out for testing
    }

    /**
     * Observe the Todo list from the repository and update the state.
     */
    private fun observeTodoList() {
        observeTodoJob?.cancel()
        observeTodoJob = todoRepository
            .getTodoList()
            .onEach { list ->
                _state.update { currentState ->
                    currentState.copy(
                        todoList = list.filter { !it.finished },
                        completeList = list.filter { it.finished }
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    /**
     * Handle UI actions.
     */
    fun onAction(action: TodoListAction) {
        when (action) {
            is TodoListAction.OnTabSelected -> updateSelectedTab(action.index)
            is TodoListAction.OnCheckedChange -> toggleTodoCompletion(action.todoItem)
            is TodoListAction.OnAddTodoClick -> addTodo() // Implement add functionality
            is TodoListAction.OnTodoClick -> openTodoDetails(action.todoItem) // Implement details navigation
        }
    }

    /**
     * Add sample data (used for testing or initial state).
     */
    private fun populateSampleData() {
        viewModelScope.launch {
            val sampleData = listOf(
                TodoItem("1", "Task 1", "Details for task 1", "2024-12-01", false),
                TodoItem("2", "Task 2", "Details for task 2", "2024-12-02", false),
                TodoItem("3", "Task 3", "Details for task 3", "2024-11-30", false)
            )
            sampleData.forEach { todoRepository.upsertTodo(it) }
        }
    }

    /**
     * Update the selected tab index in the state.
     */
    private fun updateSelectedTab(index: Int) {
        _state.update { it.copy(selectedTabIndex = index) }
    }

    /**
     * Toggle the completion state of a Todo item and update the repository.
     */
    private fun toggleTodoCompletion(todoItem: TodoItem) {
        viewModelScope.launch {
            val updatedItem = todoItem.copy(finished = !todoItem.finished)
            todoRepository.upsertTodo(updatedItem)
        }
    }

    /**
     * Add a new Todo item (stub for now).
     */
    private fun addTodo() {
        // Add logic to handle new Todo creation.
    }

    /**
     * Open details of a Todo item (stub for now).
     */
    private fun openTodoDetails(todoItem: TodoItem) {
        // Navigate to details screen with the provided Todo item.
    }
}
