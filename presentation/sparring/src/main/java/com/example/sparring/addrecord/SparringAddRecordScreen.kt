package com.example.sparring.addrecord

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sparring.record.SparringRecordEffect
import com.seungsu.common.component.CollectContent
import com.seungsu.common.ext.toastS
import com.seungsu.design.ThemePreview
import com.seungsu.design.component.DongsaniTextField
import com.seungsu.design.component.DongsaniTopAppbar
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.resource.R

@Composable
fun SparringAddRecordScreen(
    viewModel: SparringAddRecordViewModel = hiltViewModel(),
    onNavPopback: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val action by remember { mutableStateOf(viewModel::dispatch) }
    val context = LocalContext.current

    CollectContent(
        viewModel = viewModel,
        processEffect = { effect ->
            when (effect) {
                SparringAddRecordEffect.OnSaveSparringRecord -> {
                    context.toastS("아직 미구현~~")
                    onNavPopback()
                }
            }
        }
    )

    Scaffold(
        topBar = {
            DongsaniTopAppbar(
                titleContent = {
                    Text(
                        text = stringResource(R.string.add_sparring_record_title),
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
        containerColor = DongsaniTheme.colors.background.groupedUpperBase,
        bottomBar = {
            Button(
                onClick = { action(SparringAddRecordIntent.OnClickSaveSparringRecord) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = DongsaniTheme.colors.system.inverse
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(4.dp),
                enabled = uiState.isSavedEnabled
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    style = DongsaniTheme.typos.bold.font14,
                    color = DongsaniTheme.colors.label.onTintPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        with(uiState) {
            SparringAddRecordLoaded(
                modifier = Modifier.padding(paddingValues),
                gymName = gymName,
                opponentName = opponentName,
                memo = memo,
                onUiAction = action
            )
        }
    }
}

@Composable
fun SparringAddRecordLoaded(
    modifier: Modifier = Modifier,
    gymName: String,
    opponentName: String,
    memo: String,
    onUiAction: (SparringAddRecordIntent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            text = stringResource(id = R.string.sparring_gym_name_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        DongsaniTextField(
            value = gymName,
            onValueChange = { onUiAction(SparringAddRecordIntent.OnChangeGymName(it)) },
            textStyle = DongsaniTheme.typos.regular.font12.copy(
                color = DongsaniTheme.colors.label.onBgPrimary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_gym_name_placeholder),
                    style = DongsaniTheme.typos.regular.font12,
                    color = DongsaniTheme.colors.label.onBgTertiary
                )
            },
            trailingIcon = {
                if (gymName.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onUiAction(SparringAddRecordIntent.OnClearGymName) },
                        tint = DongsaniTheme.colors.label.onBgSecondary,
                        contentDescription = "close"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Text(
            text = stringResource(id = R.string.sparring_name_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        DongsaniTextField(
            value = opponentName,
            onValueChange = { onUiAction(SparringAddRecordIntent.OnChangeOpponentName(it)) },
            textStyle = DongsaniTheme.typos.regular.font12.copy(
                color = DongsaniTheme.colors.label.onBgPrimary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_name_placeholder),
                    style = DongsaniTheme.typos.regular.font12,
                    color = DongsaniTheme.colors.label.onBgTertiary
                )
            },
            trailingIcon = {
                if (opponentName.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onUiAction(SparringAddRecordIntent.OnClearOpponentName) },
                        tint = DongsaniTheme.colors.label.onBgSecondary,
                        contentDescription = "close"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Text(
            text = stringResource(id = R.string.sparring_memo_label),
            style = DongsaniTheme.typos.regular.font12,
            color = DongsaniTheme.colors.label.onBgPrimary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        DongsaniTextField(
            value = memo,
            onValueChange = { onUiAction(SparringAddRecordIntent.OnChangeMemo(it)) },
            textStyle = DongsaniTheme.typos.regular.font12.copy(
                color = DongsaniTheme.colors.label.onBgPrimary,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 16.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.sparring_memo_placeholder),
                    style = DongsaniTheme.typos.regular.font12,
                    color = DongsaniTheme.colors.label.onBgTertiary
                )
            },
            trailingIcon = {
                if (memo.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        modifier = Modifier
                            .size(12.dp)
                            .clickable { onUiAction(SparringAddRecordIntent.OnClearMemo) },
                        tint = DongsaniTheme.colors.label.onBgSecondary,
                        contentDescription = "close"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}

@ThemePreview
@Composable
fun SparringAddRecordLoadedPreview() {
    DongsaniTheme {
        SparringAddRecordLoaded(
            modifier = Modifier.padding(16.dp),
            gymName = "gym",
            opponentName = "",
            memo = ""
        )
    }
}