package com.example.sparring.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sparring.model.SparringResult
import com.seungsu.common.ext.noRippleClickable
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R as resourceR

@Composable
fun SparringResultInfo(
    modifier: Modifier = Modifier,
    win: Int,
    learn: Int,
    onClickSparringRecord: () -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = DongsaniTheme.colors.overlay.thick.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(6.dp)
                )
                .height(70.dp)
                .noRippleClickable { onClickSparringRecord() },
            contentAlignment = Alignment.Center
        ) {
            if (win + learn == 0){
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
                        value = win + learn
                    )
                    ResultItem(
                        label = stringResource(id = resourceR.string.record_win),
                        value = win
                    )
                    ResultItem(
                        label = stringResource(id = resourceR.string.record_learn),
                        value = learn
                    )
                }
            }
        }
    }
}

@ThemePreview
@Composable
private fun SparringResultInfoPreview() {
    DongsaniTheme {
        SparringResultInfo(
            modifier = Modifier.padding(16.dp),
            win = 2,
            learn = 1
        )
    }
}
