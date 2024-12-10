package com.numpol.cmptodo.todo.presentation.todo_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmptodo.composeapp.generated.resources.Res
import cmptodo.composeapp.generated.resources.no_complete_list
import cmptodo.composeapp.generated.resources.no_todo_list
import cmptodo.composeapp.generated.resources.tab_complete_list
import cmptodo.composeapp.generated.resources.tab_todo_list
import com.numpol.cmptodo.core.presentation.DesertWhite
import com.numpol.cmptodo.core.presentation.SandYellow
import com.numpol.cmptodo.todo.domain.TodoItem
import com.numpol.cmptodo.todo.presentation.todo_list.components.PageView
import com.numpol.cmptodo.todo.presentation.todo_list.components.TodoListView
import org.jetbrains.compose.resources.stringResource

@Composable
fun TodoListScreenRoot(
    viewModel: TodoListViewModel,
    onTodoClick: (TodoItem) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Use PageView component
    TodoListScreen(
        todoList = state.todoList,
        completeList = state.completeList,
        selectedTabIndex = state.selectedTabIndex,
        onTodoClick = onTodoClick,
        onCheckedChange = viewModel::onCheckedChange,
        onTabSelected = viewModel::onTabSelected
    )
}

@Composable
fun TodoListScreen(
    todoList: List<TodoItem>,
    completeList: List<TodoItem>,
    selectedTabIndex: Int,
    onTodoClick: (TodoItem) -> Unit,
    onCheckedChange: (TodoItem) -> Unit,
    onTabSelected: (Int) -> Unit,
) {
    val pagerState = rememberPagerState { 2 }
    val todoListState = rememberLazyListState()
    val completeListState = rememberLazyListState()

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        onTabSelected(pagerState.currentPage)
    }

    PageView(
        tabNames = listOf(
            stringResource(Res.string.tab_todo_list),
            stringResource(Res.string.tab_complete_list)
        ),
        selectedTabIndex = selectedTabIndex,
        pagerState = pagerState,
        tabContainerColor = DesertWhite,
        tabIndicatorColor = SandYellow,
        onTabSelected = onTabSelected
    ) { pageIndex ->
        when (pageIndex) {
            0 -> {
                if (todoList.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.no_todo_list),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                } else {
                    TodoListView(
                        todoList = todoList,
                        onTodoClick = onTodoClick,
                        onCheckedChange = onCheckedChange,
                        modifier = Modifier.fillMaxSize(),
                        scrollState = todoListState
                    )
                }
            }

            1 -> {
                if (completeList.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.no_complete_list),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                } else {
                    TodoListView(
                        todoList = completeList,
                        onTodoClick = onTodoClick,
                        onCheckedChange = onCheckedChange,
                        modifier = Modifier.fillMaxSize(),
                        scrollState = completeListState
                    )
                }
            }
        }
    }
}
