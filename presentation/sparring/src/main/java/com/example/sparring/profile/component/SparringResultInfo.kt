package com.example.sparring.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sparring.model.SparringResult
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R as resourceR

@Composable
fun SparringResultInfo(
    modifier: Modifier = Modifier,
    resultInfo: SparringResult,
    onClickEditSparringResult: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = resourceR.string.my_sparring_result),
                style = DongsaniTheme.typos.regular.font14,
                color = DongsaniTheme.colors.label.onBgPrimary,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = onClickEditSparringResult
            ) {
                Icon(
                    painter = painterResource(id = resourceR.drawable.ic_right),
                    modifier = Modifier.size(20.dp),
                    tint = DongsaniTheme.colors.label.onBgPrimary,
                    contentDescription = "edit record"
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = DongsaniTheme.colors.overlay.thick.copy(alpha = 0.2f)
                )
                .height(70.dp),
            contentAlignment = Alignment.Center
        ) {
            if (resultInfo.total == 0){
                Text(
                    text = stringResource(id = resourceR.string.record_sparring),
                    style = DongsaniTheme.typos.regular.font14,
                    color = DongsaniTheme.colors.label.onBgPrimary,
                    textAlign = TextAlign.Center
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    ResultItem(
                        label = stringResource(id = resourceR.string.record_total),
                        value = resultInfo.total
                    )
                    ResultItem(
                        label = stringResource(id = resourceR.string.record_win),
                        value = resultInfo.win
                    )
                    ResultItem(
                        label = stringResource(id = resourceR.string.record_learn),
                        value = resultInfo.learn
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun SparringResultInfoPreview() {
    DongsaniTheme {
        SparringResultInfo(
            modifier = Modifier.padding(16.dp),
            resultInfo = SparringResult(
                win = 2,
                learn = 1
            )
        )
    }
}
