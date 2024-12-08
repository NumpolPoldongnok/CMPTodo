package com.numpol.cmptodo.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.numpol.cmptodo.todo.presentation.todo_list.TodoListScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.TodoGraph
        ) {
            navigation<Route.TodoGraph>(
                startDestination = Route.TodoList
            ) {
                composable<Route.TodoList> {
                }

                composable<Route.TodoDetail> { entry ->
//                    val args = entry.toRoute<Route.TodoDetail>()
//                    val id = args.id
//                    val todoItem = todoItems.find { it.id == id }.toString()

                }
            }
        }
    }
}