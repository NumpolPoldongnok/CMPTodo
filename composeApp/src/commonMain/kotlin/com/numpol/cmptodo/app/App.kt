package com.numpol.cmptodo.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

data class TodoItem(
    val id: String,
    val title: String,
    val detail: String,
    val date: String,
    val finished: Boolean
)

val todoItems: List<TodoItem> = (0..10).map {
    val value = it.toString()
    TodoItem(
        id = value,
        title = "Todo $value",
        detail = "Detail $value",
        date = "Date $value",
        finished = false
    )
}


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
                    LazyColumn(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(todoItems, key = { it.id }) {
                            Button(
                                onClick = {
                                    navController.navigate(Route.TodoDetail(it.id))
                                }) {
                                Text(it.title)
                            }
                        }
                    }
                }

                composable<Route.TodoDetail> { entry ->
                    val args = entry.toRoute<Route.TodoDetail>()
                    val id = args.id
                    val todoItem = todoItems.find { it.id == id }.toString()
                    Box(Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        Column {
                            Text(todoItem)
                            Button(onClick = { navController.navigateUp()} ) {
                                Text("Back")
                            }
                        }
                    }
                }
            }
        }
    }
}