package com.seungsu.design.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun DongsaniTopCenterAppBar(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    titleContent: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = titleContent,
            navigationIcon = navigationIcon,
            colors = colors,
            actions = actions
        )
        if (thickness != 0.dp) {
            Divider()
        }
    }
}

@ThemePreview
@Composable
private fun DongsaniTopCenterAppBarPreview() {
    DongsaniTheme {
        DongsaniTopCenterAppBar(
            titleContent = { Text("DongsaniTopCenterAppBar")  },
            modifier = Modifier.padding(16.dp)
        )
    }
}
