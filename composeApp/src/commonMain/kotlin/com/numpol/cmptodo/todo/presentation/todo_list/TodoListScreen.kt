package com.numpol.cmptodo.todo.presentation.todo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    onTodoClick: (TodoItem) -> Unit,
) {
    // Manage the state of the to-do list and completed list
    val todoList = remember {
        mutableStateListOf(
            TodoItem("1", "Task 1", "Details for task 1", "2024-12-01", finished = false),
            TodoItem("2", "Task 2", "Details for task 2", "2024-12-02", finished = false),
            TodoItem("3", "Task 3", "Details for task 3", "2024-11-30", finished = false)
        )
    }
    val completeList = remember { mutableStateListOf<TodoItem>() }
    val selectedTabIndex = remember { mutableStateOf(0) }

    TodoListScreen(
        todoList = todoList,
        completeList = completeList,
        selectedTabIndex = selectedTabIndex.value,
        onTodoClick = onTodoClick,
        onCheckedChange = { todoItem ->
            if (todoItem.finished) {
                // Mark as incomplete: move from completeList to todoList
                completeList.remove(todoItem)
                todoList.add(todoItem.copy(finished = false))
            } else {
                // Mark as complete: move from todoList to completeList
                todoList.remove(todoItem)
                completeList.add(todoItem.copy(finished = true))
            }
        },
        onTabSelected = { selectedTabIndex.value = it }
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

@Composable
fun TodoListScreenPageView(
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .widthIn(max = 700.dp)
                .fillMaxWidth(),
            containerColor = DesertWhite,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    color = SandYellow,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                )
            }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = {
                    onTabSelected(0)
                },
                modifier = Modifier.weight(1f),
                selectedContentColor = SandYellow,
                unselectedContentColor = Color.Black.copy(alpha = 0.5f)
            ) {
                Text(
                    text = stringResource(Res.string.tab_todo_list),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
            }
            Tab(
                selected = selectedTabIndex == 1,
                onClick = {
                    onTabSelected(1)
                },
                modifier = Modifier.weight(1f),
                selectedContentColor = SandYellow,
                unselectedContentColor = Color.Black.copy(alpha = 0.5f)
            ) {
                Text(
                    text = stringResource(Res.string.tab_complete_list),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { pageIndex ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
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
    }
}
