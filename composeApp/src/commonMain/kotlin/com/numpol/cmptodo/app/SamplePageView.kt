package com.numpol.cmptodo.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cmptodo.composeapp.generated.resources.Res
import cmptodo.composeapp.generated.resources.tab_complete_list
import cmptodo.composeapp.generated.resources.tab_todo_list
import com.numpol.cmptodo.todo.presentation.todo_list.components.PageView
import org.jetbrains.compose.resources.stringResource


@Composable
fun SamplePageView() {
    val pagerState = rememberPagerState { 4 }

    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    PageView(
        tabNames = listOf(
            "Tab 1",
            "Tab 2",
            "Tab 3",
            "Tab 4",
        ),
        selectedTabIndex = selectedTabIndex,
        pagerState = pagerState,
        tabContainerColor = Color.Blue,
        tabIndicatorColor = Color.Green,
        onTabSelected = {
            selectedTabIndex = it
        }
    ) { pageIndex ->
        when (pageIndex) {
            0 -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red),
                    contentAlignment = Alignment.Center) {
                    Text("Page $pageIndex")
                }
            }

            1 -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
                    contentAlignment = Alignment.Center) {
                    Text("Page $pageIndex")
                }
            }

            2 -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue),
                    contentAlignment = Alignment.Center) {
                    Text("Page $pageIndex")
                }
            }

            3 -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Cyan),
                    contentAlignment = Alignment.Center) {
                    Text("Page $pageIndex")
                }
            }

        }
    }
}