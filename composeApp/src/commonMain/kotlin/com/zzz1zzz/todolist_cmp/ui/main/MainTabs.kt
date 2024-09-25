package com.zzz1zzz.todolist_cmp.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainTabs(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
    ) {
        Tab(
            selected = 0 == pagerState.currentPage,
            text = { Text(text = "Active") },
            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
        )
        Tab(
            selected = 1 == pagerState.currentPage,
            text = { Text(text = "Completed") },
            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
        )
    }
}