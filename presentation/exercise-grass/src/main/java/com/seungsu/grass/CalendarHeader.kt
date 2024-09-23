package com.seungsu.grass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import com.seungsu.resource.R as resourceR

@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    date: YearMonth,
    onUiAction: (ExerciseGrassIntent) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { onUiAction(ExerciseGrassIntent.OnClickMinusMonth) }
            ) {
                Icon(
                    painter = painterResource(id = resourceR.drawable.ic_left),
                    modifier = Modifier.size(24.dp),
                    tint = DongsaniTheme.colors.label.onBgPrimary,
                    contentDescription = "calendar left"
                )
            }
            Text(
                text = date.format(DateTimeFormatter.ofPattern("yy.MM")),
                style = DongsaniTheme.typos.bold.font16,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            IconButton(
                onClick = { onUiAction(ExerciseGrassIntent.OnClickPlusMonth) }
            ) {
                Icon(
                    painter = painterResource(id = resourceR.drawable.ic_right),
                    modifier = Modifier.size(24.dp),
                    tint = DongsaniTheme.colors.label.onBgPrimary,
                    contentDescription = "calendar left"
                )
            }
        }

        Icon(
            painter = painterResource(id = resourceR.drawable.ic_calendar),
            contentDescription = "calendar",
            modifier = Modifier.size(24.dp),
            tint = DongsaniTheme.colors.label.onBgPrimary
        )
    }
}

@ThemePreview
@Composable
fun CalendarHeaderPreview() {
    DongsaniTheme {
        CalendarHeader(
            modifier = Modifier.padding(16.dp),
            date = YearMonth.now()
        )
    }
}
