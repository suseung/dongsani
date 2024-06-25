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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.resource.R
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun RecordHeader(
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
                style = TextStyle(
                    fontSize = 16.sp,
                    color = DongsaniTheme.color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(id = R.string.memo),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = DongsaniTheme.color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(id = R.string.record),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = DongsaniTheme.color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun RecordHeaderPreview() {
    DongsaniTheme {
        RecordHeader(
            modifier = Modifier.padding(16.dp)
        )
    }
}
