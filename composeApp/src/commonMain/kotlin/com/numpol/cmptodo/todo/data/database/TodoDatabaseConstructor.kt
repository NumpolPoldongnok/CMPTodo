package com.numpol.cmptodo.todo.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TodoDatabaseConstructor: RoomDatabaseConstructor<TodoDatabase> {
    override fun initialize(): TodoDatabase
}