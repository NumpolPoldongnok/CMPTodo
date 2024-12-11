package com.numpol.cmptodo.todo.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Upsert
    suspend fun upsert(book: TodoEntity)

    @Query("SELECT * FROM TodoEntity")
    fun getTodoList(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getTodo(id: String): TodoEntity?

    @Query("DELETE FROM TodoEntity WHERE id = :id")
    suspend fun deleteTodo(id: String)
}