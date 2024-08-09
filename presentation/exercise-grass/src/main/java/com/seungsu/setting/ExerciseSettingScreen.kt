package com.seungsu.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seungsu.common.GrassIcon
import com.seungsu.design.component.DongsaniBottomSheet
import com.seungsu.design.component.DongsaniTopAppbar
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.design.theme.Purple
import com.seungsu.model.GrassColors
import com.seungsu.resource.R as resourceR

@Composable
fun ExerciseSettingScreen(
    viewModel: ExerciseSettingViewModel = hiltViewModel(),
    onNavPopback: () -> Unit
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
                            text = stringResource(id = resourceR.string.exercise_setting_title),
                            style = DongsaniTheme.typos.regular.font18,
                            color = DongsaniTheme.colors.label.onBgPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(end = 32.dp)
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(20.dp)
                            .clickable { onNavPopback() },
                        painter = painterResource(id = resourceR.drawable.ic_arrow_back),
                        tint = DongsaniTheme.colors.label.onBgPrimary,
                        contentDescription = "back"
                    )
                }
            )
        }
    ) { paddingValues ->
        ExerciseSettingLoaded(
            modifier = Modifier.padding(paddingValues),
            color = uiState.color,
            colorText = uiState.colorText
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseSettingLoaded(
    modifier: Modifier = Modifier,
    color: Color,
    colorText: String
) {
    var isColorChangeSheetOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = resourceR.string.exercise_grass_color),
                style = DongsaniTheme.typos.bold.font16,
                color = DongsaniTheme.colors.label.onBgPrimary
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { isColorChangeSheetOpen = true }
            ) {
                GrassIcon(color = color)
                Text(
                    text = colorText,
                    style = DongsaniTheme.typos.bold.font16,
                    color = DongsaniTheme.colors.label.onBgPrimary,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Icon(
                    painter = painterResource(id = resourceR.drawable.ic_right),
                    contentDescription = "theme color"
                )
            }
        }
    }
    if (isColorChangeSheetOpen) {
        DongsaniBottomSheet(
            onDismissRequest = { isColorChangeSheetOpen = false }
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
                        text = stringResource(id = resourceR.string.exercise_grass_color),
                        style = DongsaniTheme.typos.bold.font16,
                        color = DongsaniTheme.colors.label.onBgPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    IconButton(
                        onClick = { isColorChangeSheetOpen = false },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = resourceR.drawable.ic_close),
                            contentDescription = "close",
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
                repeat(GrassColors.size) { index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GrassIcon(
                            color = Color(android.graphics.Color.parseColor(GrassColors[index].color))
                        )
                        Spacer(
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = GrassColors[index].text,
                            style = DongsaniTheme.typos.bold.font16,
                            color = DongsaniTheme.colors.label.onBgPrimary
                        )
                    }
                    if (index != GrassColors.lastIndex) {
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun ExerciseSettingLoadedPreviwe() {
    DongsaniTheme {
        ExerciseSettingLoaded(
            modifier = Modifier.padding(16.dp),
            color = Purple,
            colorText = "보라"
        )
    }
}
