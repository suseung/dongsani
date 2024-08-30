package com.seungsu.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@Composable
fun ExerciseRecordHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.accumultate),
                style = DongsaniTheme.typos.bold.font16,
                color = DongsaniTheme.colors.label.onBgPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(id = R.string.memo),
                style = DongsaniTheme.typos.regular.font16,
                color = DongsaniTheme.colors.label.onBgPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(id = R.string.record),
                style = DongsaniTheme.typos.regular.font16,
                color = DongsaniTheme.colors.label.onBgPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@ThemePreview
@Composable
fun ExerciseRecordHeaderPreview() {
    DongsaniTheme {
        ExerciseRecordHeader(
            modifier = Modifier.padding(16.dp)
        )
    }
}
