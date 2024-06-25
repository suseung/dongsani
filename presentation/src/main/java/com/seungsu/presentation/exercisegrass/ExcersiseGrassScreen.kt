package com.seungsu.presentation.exercisegrass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seungsu.presentation.R
import com.seungsu.presentation.component.DongsaniBottomSheet
import com.seungsu.presentation.component.DongsaniTopAppbar
import com.seungsu.presentation.exercisegrass.model.ExerciseRecordItem
import com.seungsu.presentation.theme.DongsaniTheme

@Composable
fun ExcersiseGrassMainScreen(
    viewModel: ExcersiseGrassViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    var isMemoBottomSheetVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ExerciseGrassEffect.ShowMemoBottomSheet -> isMemoBottomSheetVisible = true
            }
        }
    }

    Scaffold(
        topBar = {
            DongsaniTopAppbar(
                title = stringResource(id = R.string.exercise_grass)
            )
        },
        bottomBar = {
            when (val state = uiState) {
                is ExerciseGrassState.Loading -> Unit
                is ExerciseGrassState.Success -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.dispatch(
                                    if (state.isStart.not()) ExerciseGrassIntent.OnClickStart else ExerciseGrassIntent.OnClickStop
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DongsaniTheme.color.DarkGray
                            ),
                            modifier = Modifier
                                .background(
                                    color = DongsaniTheme.color.DarkGray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clip(RoundedCornerShape(20.dp)),
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(
                                        id = if (state.isStart.not()) R.drawable.ic_start else R.drawable.ic_stop
                                    ),
                                    modifier = Modifier.size(12.dp),
                                    contentDescription = "start"
                                )
                                Text(
                                    text = stringResource(
                                        id = if (state.isStart.not()) R.string.exercise_start else R.string.exercise_stop
                                    ),
                                    style = TextStyle(
                                        color = DongsaniTheme.color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is ExerciseGrassState.Loading -> Unit
            is ExerciseGrassState.Success -> ExcersiseGrass(
                modifier = Modifier.padding(paddingValues),
                parsedCurrentTime = state.parsedCurrentTime,
                recordItems = state.recordItems,
                isMemoBottomSheetVisible = isMemoBottomSheetVisible,
                onCloseMemoBottomSheet = {
                    isMemoBottomSheetVisible = false
                    viewModel.dispatch(ExerciseGrassIntent.OnResetTimer)
                },
                onSaveMemo = {
                    isMemoBottomSheetVisible = false
                    viewModel.dispatch(ExerciseGrassIntent.OnSaveMemo(it))
                }
            )
        }
    }
}

@Composable
fun ExcersiseGrass(
    modifier: Modifier = Modifier,
    parsedCurrentTime: String,
    recordItems: List<ExerciseRecordItem>,
    isMemoBottomSheetVisible: Boolean,
    onCloseMemoBottomSheet: () -> Unit,
    onSaveMemo: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        item { Timer(time = parsedCurrentTime) }
        if (recordItems.isNotEmpty()) {
            item { RecordHeader() }
            item { Divider(modifier = Modifier.padding(vertical = 16.dp)) }
            items(recordItems.size) { index ->
                ExerciseRecord(
                    index = index + 1,
                    recordItem = recordItems[index]
                )
                if (index != recordItems.lastIndex) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(10.dp)
                    )
                }
            }
        }
        if (isMemoBottomSheetVisible) {
            item {
                DongsaniBottomSheet(
                    onDismissRequest = onCloseMemoBottomSheet
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.exercise_record),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DongsaniTheme.color.Black,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "close",
                            tint = DongsaniTheme.color.Black,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(16.dp)
                                .align(Alignment.TopEnd)
                                .clickable { onCloseMemoBottomSheet() }
                        )
                    }
                    ExerciseRecordBottomSheetContents(
                        parsedCurrentTime = parsedCurrentTime,
                        onCloseMemoBottomSheet = onCloseMemoBottomSheet,
                        onSaveMemo = onSaveMemo,
                    )
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ExcersizeGrassPreview() {
    val lists = listOf(
        ExerciseRecordItem(
            memo = "memo1",
            recordTime = 1111L
        ),
        ExerciseRecordItem(
            memo = "memo2",
            recordTime = 2222L
        ),
        ExerciseRecordItem(
            memo = "memo3",
            recordTime = 3333L
        ),
        ExerciseRecordItem(
            memo = "memo4",
            recordTime = 4444L
        )
    )
    DongsaniTheme {
        ExcersiseGrass(
            recordItems = lists,
            parsedCurrentTime = "12:34:56",
            isMemoBottomSheetVisible = false,
            onCloseMemoBottomSheet = {},
            onSaveMemo = { _ -> }
        )
    }
}
