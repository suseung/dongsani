package com.seungsu.presentation.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.seungsu.presentation.theme.DongsaniTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DongsaniBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val minHeight = LocalConfiguration.current.screenHeightDp * 0.25

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier.heightIn(minHeight.dp),
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        dragHandle = null,
        windowInsets = BottomSheetDefaults.windowInsets
    ) {
        DongsaniTheme {
            content()
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}
