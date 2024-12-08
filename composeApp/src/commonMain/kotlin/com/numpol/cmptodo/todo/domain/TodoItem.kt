package com.numpol.cmptodo.todo.domain

data class TodoItem(
    val id: String,
    val title: String,
    val detail: String,
    val date: String,
    val finished: Boolean
)