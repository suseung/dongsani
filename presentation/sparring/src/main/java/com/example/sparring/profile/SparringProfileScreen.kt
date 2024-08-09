package com.example.sparring.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.seungsu.resource.R
import kotlinx.coroutines.launch

@Composable
fun SparringProfileScreen(
    viewModel: SparringProfileViewModel = hiltViewModel(),
    onRestart: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val action by remember { mutableStateOf(viewModel::dispatch) }
    var isMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var isRestartDialogVisible by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(true) {
        launch {
            viewModel.effect.collect { effect ->
                when (effect) {
                    SparringProfileEffect.ShowRestartDialog -> isRestartDialogVisible = true
                }
            }
        }
    }

    Scaffold(
        topBar = {
            DongsaniTopAppbar(
                titleContent = {
                    Text(
                        text = stringResource(id = R.string.sparring_profile_title),
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = DongsaniTheme.colors.label.onBgTertiary,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .padding(end = 32.dp)
                            .clickable { }
                    )
                },
                actions = {
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
                                action(SparringProfileIntent.OnChangeContent(ContentsType.EXERCISE_RECORD))
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
                                action(SparringProfileIntent.OnChangeContent(ContentsType.SPARRING))
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        with(uiState) {
            SparringProfileLoaded(
                modifier = Modifier.padding(paddingValues),
                name = name,
                nickName = nickName,
                gymName = gymName,
                onChangeName = { action(SparringProfileIntent.OnChangeName(it)) },
                onChangeNickName = { action(SparringProfileIntent.OnChangeNickName(it)) },
                onChangeGymName = { action(SparringProfileIntent.OnChangeGymName(it)) },
                onClearName = { action(SparringProfileIntent.OnClearName) },
                onClearNickName = { action(SparringProfileIntent.OnClearNickName) },
                onClearGymName = { action(SparringProfileIntent.OnClearGymName) }
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SparringProfileLoaded(
    modifier: Modifier = Modifier,
    name: String,
    nickName: String,
    gymName: String,
    onChangeName: (String) -> Unit,
    onChangeNickName: (String) -> Unit,
    onChangeGymName: (String) -> Unit,
    onClearName: () -> Unit,
    onClearNickName: () -> Unit,
    onClearGymName: () -> Unit
) {

    var isLevelBottomSheetOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.padding(top = 24.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_default_profile),
            contentDescription = "profile image",
            modifier = Modifier.size(103.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(24.dp)
        )

        Text(
            text = stringResource(id = R.string.sparring_name_label),
            style = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = name,
            onValueChange = { onChangeName(it) },
            textStyle = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_name_placeholder),
                    style = TextStyle(
                        color = DongsaniTheme.colors.label.onBgTertiary,
                        fontSize = 12.sp
                    )
                )
            },
            trailingIcon = {
                if (name.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onClearName() },
                        tint = DongsaniTheme.colors.label.onBgPrimary,
                        contentDescription = "close"
                    )
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp)
        )
        Text(
            text = stringResource(id = R.string.sparring_nickname_label),
            style = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = nickName,
            onValueChange = { onChangeNickName(it) },
            textStyle = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_nickname_placeholder),
                    style = TextStyle(
                        color = DongsaniTheme.colors.label.onBgTertiary,
                        fontSize = 12.sp
                    )
                )
            },
            trailingIcon = {
                if (nickName.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onClearNickName() },
                        tint = DongsaniTheme.colors.label.onBgPrimary,
                        contentDescription = "close"
                    )
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp)
        )
        Text(
            text = stringResource(id = R.string.sparring_gym_name_label),
            style = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = gymName,
            onValueChange = { onChangeGymName(it) },
            textStyle = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_gym_name_placeholder),
                    style = TextStyle(
                        color = DongsaniTheme.colors.label.onBgTertiary,
                        fontSize = 12.sp
                    )
                )
            },
            trailingIcon = {
                if (gymName.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onClearGymName() },
                        tint = DongsaniTheme.colors.label.onBgPrimary,
                        contentDescription = "close"
                    )
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp)
        )
        Text(
            text = stringResource(id = R.string.sparring_level_label),
            style = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { isLevelBottomSheetOpen = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.sparring_level_placeholder)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(16.dp)
        )
        Text(
            text = stringResource(id = R.string.sparring_speciality_label),
            style = TextStyle(
                color = DongsaniTheme.colors.label.onBgPrimary,
                fontSize = 12.sp,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.fillMaxWidth()
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 14.dp)
        ) {
            GhostButton(title = stringResource(id = R.string.sparring_type_all_rounder))
            GhostButton(title = stringResource(id = R.string.sparring_type_guard))
            GhostButton(title = stringResource(id = R.string.sparring_type_half_guard))
            GhostButton(title = stringResource(id = R.string.sparring_type_top))
            GhostButton(title = stringResource(id = R.string.sparring_type_lapel))
            GhostButton(title = stringResource(id = R.string.sparring_type_lapel))
            GhostButton(title = stringResource(id = R.string.sparring_type_choke))
            GhostButton(title = stringResource(id = R.string.sparring_type_physical))
            GhostButton(title = stringResource(id = R.string.sparring_type_unique))
            GhostButton(title = stringResource(id = R.string.sparring_type_submission))
        }
        Spacer(
            modifier = Modifier.padding(top = 24.dp)
        )
    }

    if (isLevelBottomSheetOpen) {
        DongsaniBottomSheet(
            onDismissRequest = { isLevelBottomSheetOpen = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.sparring_level_bottomsheet_title),
                        style = TextStyle(
                            color = DongsaniTheme.colors.label.onBgPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { isLevelBottomSheetOpen = false },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "close",
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
                Divider()
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.sparring_belt_label),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = DongsaniTheme.colors.label.onBgPrimary
                    )
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    GhostButton(title = stringResource(id = R.string.sparring_belt_white))
                    GhostButton(title = stringResource(id = R.string.sparring_belt_blue))
                    GhostButton(title = stringResource(id = R.string.sparring_belt_purple))
                    GhostButton(title = stringResource(id = R.string.sparring_belt_brown))
                    GhostButton(title = stringResource(id = R.string.sparring_belt_black))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(16.dp)
                )
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
                    GhostButton(title = stringResource(id = R.string.sparring_grau_1))
                    GhostButton(title = stringResource(id = R.string.sparring_grau_2))
                    GhostButton(title = stringResource(id = R.string.sparring_grau_3))
                    GhostButton(title = stringResource(id = R.string.sparring_grau_4))
                }
            }
        }
    }
}

@Composable
fun GhostButton(
    title: String,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = DongsaniTheme.colors.label.onBgPrimary
        ),
        border = BorderStroke(
            width = 1.dp,
            color = DongsaniTheme.colors.label.onBgPrimary
        ),
        contentPadding = PaddingValues(
            vertical = 6.dp,
            horizontal = 12.dp
        )
    ) {
        Text(text = title)
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun GhostButtonPreview() {
    GhostButton(
        title = "White",
        onClick = {}
    )
}
