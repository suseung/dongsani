package com.seungsu.common.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.seungsu.common.base.MVIViewModel
import com.seungsu.common.base.ViewEffect
import com.seungsu.common.ext.toastS
import kotlinx.coroutines.launch

@Composable
fun CollectContent(
    viewModel: MVIViewModel<*, *, *>,
    processEffect: (ViewEffect) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        launch {
            viewModel.effect.collect { processEffect(it) }
        }
        launch {
            viewModel.toastEffect.collect { context.toastS(it) }
        }
    }
}
