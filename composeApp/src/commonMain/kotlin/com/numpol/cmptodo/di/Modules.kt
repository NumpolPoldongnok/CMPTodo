package com.numpol.cmptodo.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.numpol.cmptodo.core.data.HttpClientFactory
import com.numpol.cmptodo.mars_photo.data.network.KtorRemoteMarsPhotoDataSource
import com.numpol.cmptodo.mars_photo.data.network.MarsApiService
import com.numpol.cmptodo.mars_photo.data.repository.DefaultMarsPhotoRepository
import com.numpol.cmptodo.mars_photo.domain.MarsPhotoRepository
import com.numpol.cmptodo.mars_photo.presentation.screens.MarsViewModel
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
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteMarsPhotoDataSource).bind<MarsApiService>()
    singleOf(::DefaultMarsPhotoRepository).bind<MarsPhotoRepository>()

    singleOf(::DefaultTodoRepository).bind<TodoRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<TodoDatabase>().todoDao }

    viewModelOf(::TodoListViewModel)
    viewModelOf(::MarsViewModel)
}