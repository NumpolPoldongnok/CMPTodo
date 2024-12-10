package com.numpol.cmptodo.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.numpol.cmptodo.todo.presentation.todo_list.TodoListScreenRoot
import com.numpol.cmptodo.todo.presentation.todo_list.TodoListViewModel
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
                    val viewModel = TodoListViewModel()
                    TodoListScreenRoot(viewModel, onTodoClick = {
                        navController.navigate(Route.TodoDetail(it.id))
                    })
                }

                composable<Route.TodoDetail> { entry ->
                    //Text(entry.toString())

                    SamplePageView()
                }
            }
        }
    }
}