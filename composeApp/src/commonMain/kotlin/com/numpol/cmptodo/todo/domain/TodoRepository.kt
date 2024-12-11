package com.numpol.cmptodo.todo.domain

import com.numpol.cmptodo.core.domain.DataError
import com.numpol.cmptodo.core.domain.EmptyResult
import com.numpol.cmptodo.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodo(todoId: String): Result<String?, DataError>

    fun getTodoList(): Flow<List<TodoItem>>
    fun isTodoCompleted(id: String): Flow<Boolean>
    suspend fun upsertTodo(todoItem: TodoItem): EmptyResult<DataError.Local>
    suspend fun deleteTodo(id: String)
}