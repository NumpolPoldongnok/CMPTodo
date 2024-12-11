package com.numpol.cmptodo.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.numpol.cmptodo.todo.data.database.DatabaseFactory
import com.numpol.cmptodo.todo.data.database.TodoDatabase
import com.numpol.cmptodo.todo.data.repository.DefaultTodoRepository
import com.numpol.cmptodo.todo.domain.TodoRepository
import com.numpol.cmptodo.todo.presentation.todo_list.TodoListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {

    singleOf(::DefaultTodoRepository).bind<TodoRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<TodoDatabase>().todoDao }

    viewModelOf(::TodoListViewModel)
}