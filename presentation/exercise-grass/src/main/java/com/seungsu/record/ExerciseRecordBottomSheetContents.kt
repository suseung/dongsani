package com.seungsu.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.common.ext.addStyleSafely
import com.seungsu.design.component.DongsaniOutlinedTextField
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R as resourceR

@Composable
fun ExerciseRecordBottomSheetContents(
    modifier: Modifier = Modifier,
    parsedCurrentTime: String,
    onCloseMemoBottomSheet: () -> Unit,
    onSaveMemo: (String) -> Unit
) {
    var memo by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = resourceR.drawable.ic_warning),
                contentDescription = "warning",
                modifier = Modifier.size(16.dp),
                tint = DongsaniTheme.color.Black
            )
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = resourceR.string.exercise_save_record, parsedCurrentTime))
                    val startIndex = stringResource(id = resourceR.string.exercise_save_record).indexOf("%1s")
                    addStyleSafely(
                        SpanStyle(
                            color = DongsaniTheme.color.DarkGray,
                            fontWeight = FontWeight.Bold
                        ),
                        startIndex,
                        startIndex + parsedCurrentTime.length
                    )
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    color = DongsaniTheme.color.Black
                ),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        }
        DongsaniOutlinedTextField(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            value = memo,
            onValueChange = { memo = it },
            placeholder = {
                Text(
                    text = stringResource(id = resourceR.string.exercise_memo_record_placeholder),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = DongsaniTheme.color.Gray
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DongsaniTheme.color.Gray,
                unfocusedBorderColor = DongsaniTheme.color.Gray
            )
        )
        Button(
            onClick = { onSaveMemo(memo) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DongsaniTheme.color.Purple
            )
        ) {
            Text(
                text = stringResource(id = resourceR.string.save),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = DongsaniTheme.color.White,
                    textAlign = TextAlign.Center
                )
            )
        }
        Button(
            onClick = onCloseMemoBottomSheet,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DongsaniTheme.color.LightPurple
            )
        ) {
            Text(
                text = stringResource(id = resourceR.string.no),
                style = TextStyle(
                    fontSize = 16.sp,
                    color = DongsaniTheme.color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
            )
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ExerciseRecordBottomSheetContentsPreview() {
    DongsaniTheme {
        ExerciseRecordBottomSheetContents(
            parsedCurrentTime = "12:34:32",
            onCloseMemoBottomSheet = {},
            onSaveMemo = {}
        )
    }
}
