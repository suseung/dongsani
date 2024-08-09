package com.seungsu.design.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R as resourceR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DongsaniTopAppbar(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    titleContent: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column(
        modifier = modifier
            //.systemBarsPadding()
    ) {
        TopAppBar(
            title = { titleContent() },
            navigationIcon = navigationIcon,
            actions = actions
        )
        Divider(thickness = thickness)
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun DongsaniTopAppbarPreview() {
    DongsaniTheme {
        DongsaniTopAppbar(
            titleContent = {
                Text(
                    text = "TopAppbar",
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp),
                    imageVector = ImageVector.vectorResource(id = resourceR.drawable.ic_arrow_back),
                    contentDescription = "back"
                )
            }
        )
    }
}
