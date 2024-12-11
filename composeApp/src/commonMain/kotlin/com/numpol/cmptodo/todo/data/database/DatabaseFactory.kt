package com.numpol.cmptodo.todo.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<TodoDatabase>
}