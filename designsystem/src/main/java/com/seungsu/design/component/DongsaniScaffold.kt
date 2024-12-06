package com.seungsu.design.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun DongsaniScaffold(
    modifier: Modifier = Modifier,
    topAppBarColor: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    containerColor: Color = DongsaniTheme.colors.background.defaultBase,
    navigationIcon: @Composable () -> Unit = {},
    titleContent: @Composable () -> Unit = {},
    titleThickness: Dp = 0.5.dp,
    actions: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    contents: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            DongsaniTopAppbar(
                navigationIcon = navigationIcon,
                titleContent = titleContent,
                actions = actions,
                thickness = titleThickness,
                colors = topAppBarColor
            )
        },
        bottomBar = bottomBar,
        containerColor = containerColor
    ) { paddingValues ->
        contents(paddingValues)
    }
}

@Composable
fun DongsaniScaffold(
    modifier: Modifier = Modifier,
    topAppBarColor: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    containerColor: Color = DongsaniTheme.colors.background.defaultBase,
    navigationIcon: @Composable () -> Unit = {},
    titleContent: @Composable () -> Unit = {},
    titleThickness: Dp = 0.5.dp,
    contentColor: Color = contentColorFor(containerColor),
    actions: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    contents: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            DongsaniTopCenterAppBar(
                navigationIcon = navigationIcon,
                titleContent = titleContent,
                actions = actions,
                thickness = titleThickness,
                colors = topAppBarColor
            )
        },
        bottomBar = bottomBar,
        containerColor = containerColor,
        contentColor = contentColor
    ) { paddingValues ->
        contents(paddingValues)
    }
}