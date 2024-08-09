package com.seungsu.grass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seungsu.design.component.DongsaniTopAppbar
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.design.theme.Purple
import com.seungsu.model.ExerciseRecordItem
import com.seungsu.record.ExerciseRecord
import com.seungsu.record.ExerciseRecordHeader
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import com.seungsu.resource.R as resourceR

@Composable
fun ExerciseGrassScreen(
    viewModel: ExerciseGrassViewModel = hiltViewModel(),
    navToExerciseRecord: () -> Unit,
    navigateToSetting: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            DongsaniTopAppbar(
                titleContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = resourceR.string.exercise_record_title),
                            style = DongsaniTheme.typos.regular.font18,
                            textAlign = TextAlign.Center,
                            color = DongsaniTheme.colors.label.onBgTertiary,
                            modifier = Modifier
                                .padding(end = 32.dp)
                                .clickable { navToExerciseRecord() }
                        )

                        Text(
                            text = stringResource(id = resourceR.string.exercise_grass_title),
                            style = DongsaniTheme.typos.regular.font18,
                            color = Purple,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                actions = {
                    IconButton(onClick = navigateToSetting) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = resourceR.drawable.ic_setting),
                            contentDescription = "setting",
                            modifier = Modifier.clickable { navigateToSetting() }
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp),
                        painter = painterResource(id = resourceR.drawable.ic_dongsani),
                        tint = DongsaniTheme.colors.label.onBgPrimary,
                        contentDescription = "dongsani logo"
                    )
                }
            )
        }
    ) { paddingValues ->
        when (val state = uiState) {
            ExerciseGrassState.Loading -> Unit
            is ExerciseGrassState.Success -> ExerciseGrass(
                modifier = Modifier.padding(paddingValues),
                date = state.currentYearMonth,
                lengthOfMonth = state.currentLengthOfMonth,
                currentDayOfWeek = state.currentDayOfWeek,
                currentTotalExerciseTimes = state.currentTotalExerciseTimes,
                currentSelectedExerciseRecords = state.currentSelectedExerciseRecords,
                color = state.color,
                onClickMinusMonth = { viewModel.dispatch(ExerciseGrassIntent.OnClickMinusMonth) },
                onClickPlusMonth = { viewModel.dispatch(ExerciseGrassIntent.OnClickPlusMonth) },
                onClickGrass = { viewModel.dispatch(ExerciseGrassIntent.OnClickGrass(it)) }
            )
        }
    }
}

@Composable
fun ExerciseGrass(
    modifier: Modifier = Modifier,
    date: YearMonth,
    lengthOfMonth: Int,
    currentDayOfWeek: DayOfWeek,
    currentTotalExerciseTimes: Map<LocalDate, Long>,
    currentSelectedExerciseRecords: List<ExerciseRecordItem>,
    color: Color,
    onClickMinusMonth: () -> Unit = {},
    onClickPlusMonth: () -> Unit = {},
    onClickGrass: (LocalDate) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = 32.dp,
                horizontal = 16.dp
            )
    ) {
        item {
            ExerciseGrassCalendar(
                date = date,
                lengthOfMonth = lengthOfMonth,
                currentDayOfWeek = currentDayOfWeek,
                currentTotalExerciseTimes = currentTotalExerciseTimes,
                color = color,
                onClickMinusMonth = onClickMinusMonth,
                onClickPlusMonth = onClickPlusMonth,
                onClickGrass = onClickGrass
            )
        }
        if (currentSelectedExerciseRecords.isNotEmpty()) {
            item { ExerciseRecordHeader() }
            item { Divider(modifier = Modifier.padding(vertical = 16.dp)) }
            items(currentSelectedExerciseRecords.size) { index ->
                ExerciseRecord(
                    index = index + 1,
                    recordItem = currentSelectedExerciseRecords[index]
                )
                if (index != currentSelectedExerciseRecords.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(10.dp)
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ExerciseGrassPreview() {
    DongsaniTheme {
        ExerciseGrass(
            date = YearMonth.now(),
            lengthOfMonth = 31,
            currentDayOfWeek = DayOfWeek.TUESDAY,
            currentTotalExerciseTimes = mapOf(),
            color = Purple,
            currentSelectedExerciseRecords = emptyList()
        )
    }
}
