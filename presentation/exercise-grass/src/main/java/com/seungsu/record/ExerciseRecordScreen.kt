package com.seungsu.record

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.seungsu.common.model.ContentsType
import com.seungsu.design.component.DongsaniBottomSheet
import com.seungsu.design.component.DongsaniComposeDialog
import com.seungsu.design.component.DongsaniTopAppbar
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.design.theme.Purple
import com.seungsu.model.ExerciseRecordItem
import com.seungsu.resource.R
import com.seungsu.resource.R as resourceR

@Composable
fun ExerciseRecordScreen(
    viewModel: ExerciseRecordViewModel = hiltViewModel(),
    navToExerciseGrass: () -> Unit,
    navigateToSetting: () -> Unit = {},
    onRestart: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    var isMemoBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    var isMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var isRestartDialogVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ExerciseRecordEffect.ShowMemoBottomSheet -> isMemoBottomSheetVisible = true
                ExerciseRecordEffect.ShowRestartDialog -> isRestartDialogVisible = true
            }
        }
    }

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
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Purple,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(end = 32.dp)
                        )
                        Text(
                            text = stringResource(id = resourceR.string.exercise_grass_title),
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = DongsaniTheme.colors.label.onBgTertiary,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.clickable { navToExerciseGrass() }
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
                    IconButton(
                        onClick = { isMenuExpanded = isMenuExpanded.not() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "setting",
                        )
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "운동기록",
                                    style = TextStyle(
                                        color = DongsaniTheme.colors.label.onBgPrimary
                                    )
                                )
                            },
                            onClick = {
                                viewModel.dispatch(ExerciseRecordIntent.OnChangeContent(ContentsType.EXERCISE_RECORD))
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "스파링",
                                    style = TextStyle(
                                        color = DongsaniTheme.colors.label.onBgPrimary
                                    )
                                )
                            },
                            onClick = {
                                viewModel.dispatch(ExerciseRecordIntent.OnChangeContent(ContentsType.SPARRING))
                            }
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
        },
        bottomBar = {
            when (val state = uiState) {
                is ExerciseRecordState.Loading -> Unit
                is ExerciseRecordState.Success -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.dispatch(
                                    if (state.isStart.not()) ExerciseRecordIntent.OnClickStart else ExerciseRecordIntent.OnClickStop
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DongsaniTheme.colors.label.onBgSecondary
                            ),
                            modifier = Modifier
                                .background(
                                    color = DongsaniTheme.colors.label.onBgSecondary,
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
                                        id = if (state.isStart.not()) resourceR.drawable.ic_start else resourceR.drawable.ic_stop
                                    ),
                                    modifier = Modifier.size(12.dp),
                                    contentDescription = "start"
                                )
                                Text(
                                    text = stringResource(
                                        id = if (state.isStart.not()) resourceR.string.exercise_start else resourceR.string.exercise_stop
                                    ),
                                    style = TextStyle(
                                        color = DongsaniTheme.colors.system.white,
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
            is ExerciseRecordState.Loading -> Unit
            is ExerciseRecordState.Success -> ExerciseRecord(
                modifier = Modifier.padding(paddingValues),
                parsedCurrentTime = state.parsedCurrentTime,
                recordItems = state.recordItems,
                isMemoBottomSheetVisible = isMemoBottomSheetVisible,
                onCloseMemoBottomSheet = {
                    isMemoBottomSheetVisible = false
                    viewModel.dispatch(ExerciseRecordIntent.OnResetTimer)
                },
                onSaveMemo = {
                    isMemoBottomSheetVisible = false
                    viewModel.dispatch(ExerciseRecordIntent.OnSaveMemo(it))
                }
            )
        }
    }
    if (isRestartDialogVisible) {
        DongsaniComposeDialog(
            message = stringResource(id = R.string.app_restart),
            confirmText = stringResource(id = R.string.restart_ok),
            isCancellable = false,
            onClickConfirmed = onRestart
        )
    }
}

@Composable
fun ExerciseRecord(
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
            item { ExerciseRecordHeader() }
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
                            text = stringResource(id = resourceR.string.exercise_record),
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DongsaniTheme.colors.label.onBgPrimary,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Icon(
                            painter = painterResource(id = resourceR.drawable.ic_close),
                            contentDescription = "close",
                            tint = DongsaniTheme.colors.label.onBgPrimary,
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
fun ExerciseGrassPreview() {
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
        ExerciseRecord(
            recordItems = lists,
            parsedCurrentTime = "12:34:56",
            isMemoBottomSheetVisible = false,
            onCloseMemoBottomSheet = {},
            onSaveMemo = { _ -> }
        )
    }
}
