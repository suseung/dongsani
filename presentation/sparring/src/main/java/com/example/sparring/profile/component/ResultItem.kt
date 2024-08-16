package com.example.sparring.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun ResultItem(
    modifier: Modifier = Modifier,
    label: String,
    value: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = DongsaniTheme.typos.regular.font14,
            color = DongsaniTheme.colors.label.onBgPrimary,
            modifier = Modifier.padding(bottom = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = value.toString(),
            style = DongsaniTheme.typos.bold.font24,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ResultItemPreview() {
    DongsaniTheme {
        ResultItem(
            modifier = Modifier.padding(16.dp),
            label = stringResource(id = com.seungsu.resource.R.string.record_win),
            value = 3
        )
    }
}
