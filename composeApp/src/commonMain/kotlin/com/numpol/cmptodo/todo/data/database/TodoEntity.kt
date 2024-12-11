package com.numpol.cmptodo.todo.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val detail: String,
    val imageUrl: String,
    val date: String,
    val finished: Boolean
)
