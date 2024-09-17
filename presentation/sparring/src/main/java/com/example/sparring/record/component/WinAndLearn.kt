package com.example.sparring.record.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@Composable
fun WinAndLearn(
    modifier: Modifier = Modifier,
    win: Int,
    learn: Int,
    total: Int
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        SparringRecordItem(
            modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.record_total),
            value = total
        )
        SparringRecordItem(
            modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.record_win),
            value = win
        )
        SparringRecordItem(
            modifier = Modifier.weight(1f),
            label = stringResource(id = R.string.record_learn),
            value = learn
        )
    }
}

@ThemePreview
@Composable
fun WinAndLearnPreview() {
    DongsaniTheme {
        WinAndLearn(
            modifier = Modifier.padding(16.dp),
            win = 3,
            learn = 5,
            total = 8
        )
    }
}
