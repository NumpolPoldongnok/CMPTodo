package com.numpol.cmptodo.todo.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<TodoDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(TodoDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}