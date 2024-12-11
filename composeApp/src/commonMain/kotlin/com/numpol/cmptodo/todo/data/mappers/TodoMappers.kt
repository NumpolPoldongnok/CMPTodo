package com.numpol.cmptodo.todo.data.mappers

import com.numpol.cmptodo.todo.data.database.TodoEntity
import com.numpol.cmptodo.todo.domain.TodoItem


fun TodoItem.toTodoEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        detail = detail,
        imageUrl = "",
        date = date,
        finished = finished,
    )
}

fun TodoEntity.toTodoItem(): TodoItem {
    return TodoItem(
        id = id,
        title = title,
        detail = detail,
        date = date,
        finished = finished,
    )
}