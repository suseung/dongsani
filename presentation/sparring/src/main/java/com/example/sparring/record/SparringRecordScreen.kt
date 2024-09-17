package com.example.sparring.record

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sparring.record.component.OpponentProfileBottomSheet
import com.example.sparring.record.component.SparringRecord
import com.example.sparring.model.ProfileInfo
import com.example.sparring.model.SparringRecord
import com.example.sparring.model.SparringResult
import com.example.sparring.profile.component.SparringResultInfo
import com.seungsu.common.INVALID_INT
import com.seungsu.common.component.CollectContent
import com.seungsu.common.ext.noRippleClickable
import com.seungsu.design.ThemePreview
import com.seungsu.design.component.DongsaniTopAppbar
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@Composable
fun SparringRecordScreen(
    viewModel: SparringRecordViewModel = hiltViewModel(),
    onNavPopback: () -> Unit = {},
    onNavigateToAddSparringRecord: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val action by remember { mutableStateOf(viewModel::dispatch) }

    CollectContent(
        viewModel = viewModel,
        processEffect = { effect ->
            when (effect) {}
        }
    )

    Scaffold(
        topBar = {
            DongsaniTopAppbar(
                titleContent = {
                    Text(
                        text = stringResource(R.string.sparring_record),
                        style = DongsaniTheme.typos.regular.font18,
                        color = DongsaniTheme.colors.label.onBgPrimary,
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                            .clickable { onNavPopback() },
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        tint = DongsaniTheme.colors.label.onBgPrimary,
                        contentDescription = "back"
                    )
                }
            )
        },
        containerColor = DongsaniTheme.colors.background.groupedUpperBase
    ) { paddingValues ->
        with(uiState) {
            SparringRecordLoaded(
                modifier = Modifier.padding(paddingValues),
                records = records,
                onClickAddRecord = onNavigateToAddSparringRecord
            )
        }
    }
}

@Composable
fun SparringRecordLoaded(
    modifier: Modifier = Modifier,
    records: List<SparringRecord>,
    onClickAddRecord: () -> Unit = {}
) {
    var isOpponentProfileOpen by rememberSaveable { mutableStateOf(false) }
    var selectedUserId by rememberSaveable { mutableStateOf(INVALID_INT) }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .background(color = DongsaniTheme.colors.background.defaultBase)
            ) {
                Text(
                    text = stringResource(id = R.string.sparring_record_all),
                    style = DongsaniTheme.typos.regular.font14,
                    color = DongsaniTheme.colors.label.onBgSecondary,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.sparring_record_gym),
                    style = DongsaniTheme.typos.regular.font14,
                    color = DongsaniTheme.colors.label.onBgSecondary,
                    modifier = Modifier
                        .padding(vertical = 14.dp)
                        .weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
        item {
            SparringResultInfo(
                modifier = Modifier.padding(bottom = 14.dp),
                win = records.sumOf { it.resultInfo.win },
                learn = records.sumOf { it.resultInfo.learn }
            )
        }
        items(records.size) { index ->
            with(records[index]) {
                SparringRecord(
                    profileImagePath = opponentInfo.profileImagePath,
                    profileImageUri = opponentInfo.profileImageUri,
                    userName = opponentInfo.name,
                    userNickName = opponentInfo.nickName,
                    resultInfo = records[index].resultInfo,
                    modifier = Modifier.padding(
                        bottom = if (index == records.lastIndex) 0.dp else 12.dp
                    ),
                    onClickProfile = {
                        isOpponentProfileOpen = true
                        selectedUserId = opponentInfo.userId
                    }
                )
            }
        }
        item {
            Text(
                text = stringResource(id = R.string.add_sparring_record),
                style = DongsaniTheme.typos.regular.font14,
                color = DongsaniTheme.colors.label.onBgSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .noRippleClickable { onClickAddRecord() },
                textAlign = TextAlign.Center
            )
        }
        if (isOpponentProfileOpen) {
            item {
                records.find { it.opponentInfo.userId == selectedUserId }?.let {
                    OpponentProfileBottomSheet(
                        profileInfo = it.opponentInfo,
                        onDismiss = { isOpponentProfileOpen = false }
                    )
                }
            }
        }
    }
}

@ThemePreview
@Composable
fun SparringRecordLoadedPreview() {
    DongsaniTheme {
        SparringRecordLoaded(
            modifier = Modifier.padding(16.dp),
            records = listOf(
                SparringRecord(
                    opponentInfo = ProfileInfo(
                        profileImagePath = "",
                        profileImageUri = Uri.EMPTY,
                        name = "name",
                        nickName = "nickName",
                        gymName = "gymName",
                        beltId = 0,
                        grauId = 0,
                        userId = -1,
                        playStyleIds = emptyList()

                    ),
                    resultInfo = SparringResult(
                        win = 3,
                        learn = 5
                    )
                ),
                SparringRecord(
                    opponentInfo = ProfileInfo(
                        profileImagePath = "",
                        profileImageUri = Uri.EMPTY,
                        name = "name",
                        nickName = "nickName",
                        gymName = "gymName",
                        beltId = 0,
                        grauId = 0,
                        userId = -1,
                        playStyleIds = emptyList()

                    ),
                    resultInfo = SparringResult(
                        win = 3,
                        learn = 5
                    )
                ),
                SparringRecord(
                    opponentInfo = ProfileInfo(
                        profileImagePath = "",
                        profileImageUri = Uri.EMPTY,
                        name = "name",
                        nickName = "nickName",
                        gymName = "gymName",
                        beltId = 0,
                        grauId = 0,
                        userId = -1,
                        playStyleIds = emptyList()

                    ),
                    resultInfo = SparringResult(
                        win = 3,
                        learn = 5
                    )
                ),
                SparringRecord(
                    opponentInfo = ProfileInfo(
                        profileImagePath = "",
                        profileImageUri = Uri.EMPTY,
                        name = "name",
                        nickName = "nickName",
                        gymName = "gymName",
                        beltId = 0,
                        grauId = 0,
                        userId = -1,
                        playStyleIds = emptyList()

                    ),
                    resultInfo = SparringResult(
                        win = 3,
                        learn = 5
                    )
                ),
                SparringRecord(
                    opponentInfo = ProfileInfo(
                        profileImagePath = "",
                        profileImageUri = Uri.EMPTY,
                        name = "name",
                        nickName = "nickName",
                        gymName = "gymName",
                        beltId = 0,
                        grauId = 0,
                        userId = -1,
                        playStyleIds = emptyList()

                    ),
                    resultInfo = SparringResult(
                        win = 3,
                        learn = 5
                    )
                )
            )
        )
    }
}