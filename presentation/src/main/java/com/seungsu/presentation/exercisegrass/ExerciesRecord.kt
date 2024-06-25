package com.seungsu.presentation.exercisegrass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.presentation.exercisegrass.model.ExerciseRecordItem
import com.seungsu.presentation.theme.DongsaniTheme

@Composable
fun ExerciseRecord(
    modifier: Modifier = Modifier,
    index: Int,
    recordItem: ExerciseRecordItem
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = index.toString(),
            style = TextStyle(
                fontSize = 14.sp,
                color = DongsaniTheme.color.Black,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = recordItem.memo,
            style = TextStyle(
                fontSize = 14.sp,
                color = DongsaniTheme.color.Black,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = recordItem.parsedRecordTime,
            style = TextStyle(
                fontSize = 14.sp,
                color = DongsaniTheme.color.Black,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ExerciseRecordPreview() {
    DongsaniTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            ExerciseRecord(
                index = 1,
                recordItem = ExerciseRecordItem(
                    memo = "memo1",
                    recordTime = 11L
                )
            )
            ExerciseRecord(
                index = 2,
                recordItem = ExerciseRecordItem(
                    memo = "memo1",
                    recordTime = 22L
                )
            )
            ExerciseRecord(
                index = 3,
                recordItem = ExerciseRecordItem(
                    memo = "memo3",
                    recordTime = 33L
                )
            )
            ExerciseRecord(
                index = 4,
                recordItem = ExerciseRecordItem(
                    memo = "memo4",
                    recordTime = 3644L
                )
            )
        }
    }
}
