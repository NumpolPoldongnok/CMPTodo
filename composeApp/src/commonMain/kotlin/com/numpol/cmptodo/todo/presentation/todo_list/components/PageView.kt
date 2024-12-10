package com.numpol.cmptodo.todo.presentation.todo_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PageView(
    tabNames: List<String>,
    selectedTabIndex: Int,
    pagerState: PagerState,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tabContainerColor: Color = Color.White,
    tabIndicatorColor: Color = Color.Black,
    content: @Composable (pageIndex: Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .widthIn(max = 700.dp)
                .fillMaxWidth(),
            containerColor = tabContainerColor,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    color = tabIndicatorColor,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            tabNames.forEachIndexed { index, tabName ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier.weight(1f),
                    selectedContentColor = tabIndicatorColor,
                    unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = tabName,
                        modifier = Modifier.padding(vertical = 12.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { pageIndex ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content(pageIndex)
            }
        }
    }
}
