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
        state = state,
        action = viewModel::onAction
    )
}

@Composable
fun TodoListScreen(
    state: TodoListState,
    action: (TodoListAction) -> Unit
) {
    val pagerState = rememberPagerState { 2 }
    val todoListState = rememberLazyListState()
    val completeListState = rememberLazyListState()

    LaunchedEffect(state.selectedTabIndex) {
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        action(TodoListAction.OnTabSelected(pagerState.currentPage))
    }

    PageView(
        tabNames = listOf(
            stringResource(Res.string.tab_todo_list),
            stringResource(Res.string.tab_complete_list)
        ),
        selectedTabIndex = state.selectedTabIndex,
        pagerState = pagerState,
        tabContainerColor = DesertWhite,
        tabIndicatorColor = SandYellow,
        onTabSelected = { action(TodoListAction.OnTabSelected(it)) }
    ) { pageIndex ->
        when (pageIndex) {
            0 -> {
                if (state.todoList.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.no_todo_list),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                } else {
                    TodoListView(
                        todoList = state.todoList,
                        onTodoClick = { action(TodoListAction.OnTodoClick(it)) },
                        onCheckedChange = { action(TodoListAction.OnCheckedChange(it)) },
                        modifier = Modifier.fillMaxSize(),
                        scrollState = todoListState
                    )
                }
            }

            1 -> {
                if (state.completeList.isEmpty()) {
                    Text(
                        text = stringResource(Res.string.no_complete_list),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                } else {
                    TodoListView(
                        todoList = state.completeList,
                        onTodoClick = { action(TodoListAction.OnTodoClick(it)) },
                        onCheckedChange = { action(TodoListAction.OnCheckedChange(it)) },
                        modifier = Modifier.fillMaxSize(),
                        scrollState = completeListState
                    )
                }
            }
        }
    }
}
