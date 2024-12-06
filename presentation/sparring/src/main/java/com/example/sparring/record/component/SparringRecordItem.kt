package com.example.sparring.record.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun SparringRecordItem(
    modifier: Modifier = Modifier,
    label: String,
    value: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            color = DongsaniTheme.colors.label.onBgPrimary,
            style = DongsaniTheme.typos.regular.font14
        )
        Text(
            text = value.toString(),
            color = DongsaniTheme.colors.label.onBgPrimary,
            style = DongsaniTheme.typos.bold.font14
        )
    }
}

@ThemePreview
@Composable
private fun SparringRecordItemPreview() {
    DongsaniTheme {
        SparringRecordItem(
            modifier = Modifier.padding(16.dp),
            label = "Total",
            value = 4
        )
    }
}
