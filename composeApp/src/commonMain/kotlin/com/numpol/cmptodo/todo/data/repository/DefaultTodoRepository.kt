package com.numpol.cmptodo.todo.data.repository

import androidx.sqlite.SQLiteException
import com.numpol.cmptodo.core.domain.DataError
import com.numpol.cmptodo.core.domain.EmptyResult
import com.numpol.cmptodo.core.domain.Result
import com.numpol.cmptodo.todo.data.database.TodoDao
import com.numpol.cmptodo.todo.data.mappers.toTodoEntity
import com.numpol.cmptodo.todo.data.mappers.toTodoItem
import com.numpol.cmptodo.todo.domain.TodoItem
import com.numpol.cmptodo.todo.domain.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultTodoRepository(
    private val todoDao: TodoDao
): TodoRepository {
    override suspend fun getTodo(todoId: String): Result<String?, DataError> {
        val localResult = todoDao.getTodo(todoId)

        return if(localResult == null) {
            Result.Error(DataError.Local.UNKNOWN)
        } else  {
            Result.Success(localResult.detail)
        }

    }

    override fun getTodoList(): Flow<List<TodoItem>> {
        return todoDao
            .getTodoList()
            .map { todoEntities ->
                todoEntities.map { it.toTodoItem() }
            }
    }

    override fun isTodoCompleted(id: String): Flow<Boolean> {
        return todoDao
            .getTodoList()
            .map { todoEntities ->
                todoEntities.any { it.id == id }
            }
    }

    override suspend fun upsertTodo(todoItem: TodoItem): EmptyResult<DataError.Local> {
        return try {
            todoDao.upsert(todoItem.toTodoEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteTodo(id: String) {
        todoDao.deleteTodo(id)
    }
}