package com.example.sparring.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sparring.model.BeltType
import com.example.sparring.model.GrauType
import com.example.sparring.model.PlayStyle
import com.example.sparring.model.SparringResult
import com.example.sparring.profile.component.ProfileInfo
import com.example.sparring.profile.component.SparringResultInfo
import com.seungsu.common.eventbus.Event
import com.seungsu.common.eventbus.EventBusEntryPoint
import com.seungsu.core.CollectContent
import com.seungsu.design.component.DongsaniTopAppbar
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateToMakeProfile: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val action by remember { mutableStateOf(viewModel::dispatch) }
    val context = LocalContext.current
    val eventBus = EventBusEntryPoint.resolve(context).getEventBus()

    LaunchedEffect(true) {
        eventBus.collect {
            when (it) {
                Event.Sparring.OnProfileChanged -> action(ProfileIntent.OnRefresh)
            }

        }
    }
    CollectContent(
        viewModel = viewModel,
        processEffect = { effect ->
            when (effect) {

            }
        }
    )

    Scaffold(
        topBar = {
            DongsaniTopAppbar(
                titleContent = {
                    Text(
                        text = stringResource(id = com.seungsu.resource.R.string.mypage),
                        style = DongsaniTheme.typos.regular.font18,
                        color = DongsaniTheme.colors.label.onBgPrimary,
                        textAlign = TextAlign.Center
                    )
                },
                actions = {}
            )
        },
        containerColor = DongsaniTheme.colors.background.groupedUpperBase
    ) { paddingValues ->
        with(uiState) {
            ProfileLoaded(
                modifier = Modifier.padding(paddingValues),
                profileImagePath = profileImagePath,
                userName = name,
                userNickName = nickName,
                gymName = gymName,
                currentBelt = currentBelt,
                currentGrau = currentGrau,
                currentPlayStyles = currentPlayStyles,
                sparringResult = sparringResult,
                onClickEdit = onNavigateToMakeProfile,
                onClickEditSparringResult = {}
            )
        }
    }
}

@Composable
fun ProfileLoaded(
    modifier: Modifier = Modifier,
    profileImagePath: String,
    userName: String,
    userNickName: String,
    gymName: String,
    currentBelt: BeltType,
    currentGrau: GrauType,
    currentPlayStyles: List<PlayStyle>,
    sparringResult: SparringResult,
    onClickEdit: () -> Unit = {},
    onClickEditSparringResult: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ProfileInfo(
            profileImagePath = profileImagePath,
            userName = userName,
            userNickName = userNickName,
            gymName = gymName,
            currentBelt = currentBelt,
            currentGrau = currentGrau,
            currentPlayStyles = currentPlayStyles,
            onClickEditProfile = onClickEdit
        )
        Spacer(
            modifier = Modifier.size(64.dp)
        )
        SparringResultInfo(
            resultInfo = sparringResult,
            onClickEditSparringResult = onClickEditSparringResult
        )
    }
}


@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ProfileLoadedPreview() {
    DongsaniTheme {
        ProfileLoaded(
            profileImagePath = "",
            userName = "name",
            userNickName = "nickName",
            gymName = "gymName",
            currentBelt = BeltType.PURPLE,
            currentGrau = GrauType.THREE,
            currentPlayStyles = listOf(
                PlayStyle(
                    id = 1,
                    styleName = "Guard"
                ),
                PlayStyle(
                    id = 2,
                    styleName = "Half-Guard"
                ),
                PlayStyle(
                    id = 3,
                    styleName = "Top"
                ),
            ),
            sparringResult = SparringResult()
        )
    }
}
