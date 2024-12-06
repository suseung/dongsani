package com.example.sparring.profile.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.component.VerticalSeparator
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun NameAndNickName(
    modifier: Modifier = Modifier,
    userName: String,
    userNickName: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = userName,
            style = DongsaniTheme.typos.bold.font16,
            color = DongsaniTheme.colors.label.onBgPrimary
        )
        VerticalSeparator(
            modifier = Modifier.padding(horizontal = 20.dp),
            color = DongsaniTheme.colors.label.onBgTertiary
        )
        Text(
            text = userNickName,
            style = DongsaniTheme.typos.regular.font14,
            color = DongsaniTheme.colors.label.onBgSecondary
        )
    }
}

@ThemePreview
@Composable
private fun NameAndNickNamePreview() {
    DongsaniTheme {
        NameAndNickName(
            modifier = Modifier.padding(16.dp),
            userName = "name",
            userNickName = "nickName"
        )
    }
}
