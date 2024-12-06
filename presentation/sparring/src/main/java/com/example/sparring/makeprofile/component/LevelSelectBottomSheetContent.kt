package com.example.sparring.makeprofile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sparring.model.BELTs
import com.example.sparring.model.GRAUs
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LevelSelectBottomSheetContent(
    modifier: Modifier = Modifier,
    currentBeltId: Int,
    currentGrauId: Int,
    onClickClose: () -> Unit = {},
    onClickSave: (Int, Int) -> Unit = { _, _ -> }
) {
    var beltId by rememberSaveable { mutableStateOf(currentBeltId) }
    var grauId by rememberSaveable { mutableStateOf(currentGrauId) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.sparring_level_bottomsheet_title),
                style = DongsaniTheme.typos.bold.font16,
                color = DongsaniTheme.colors.label.onBgPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
            IconButton(
                onClick = onClickClose,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "close",
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp)
        )
        Text(
            text = stringResource(id = R.string.sparring_belt_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            repeat(BELTs.size) { index ->
                SelectItem(
                    content = BELTs[index].belt.code,
                    isSelected = beltId == BELTs[index].id,
                    onClickItem = { beltId = BELTs[index].id }
                )
            }
        }
        Text(
            text = stringResource(id = R.string.sparring_grau_label),
            style = TextStyle(
                fontSize = 12.sp,
                color = DongsaniTheme.colors.label.onBgPrimary
            )
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            repeat(GRAUs.size) { index ->
                SelectItem(
                    content = GRAUs[index].grau.code,
                    isSelected = grauId == GRAUs[index].id,
                    onClickItem = { grauId = GRAUs[index].id }
                )
            }
        }
        Button(
            onClick = { onClickSave(beltId, grauId) },
            colors = ButtonDefaults.buttonColors(
                containerColor = DongsaniTheme.colors.system.inverse
            ),
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "저장하기",
                style = DongsaniTheme.typos.bold.font14,
                color = DongsaniTheme.colors.label.onTintPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@ThemePreview
@Composable
private fun LevelSelectBottomSheetContentPreview() {
    DongsaniTheme {
        LevelSelectBottomSheetContent(
            currentBeltId = -1,
            currentGrauId = -1
        )
    }
}
