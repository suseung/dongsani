package com.seungsu.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.design.theme.Purple

@Composable
fun DongsaniComposeDialog(
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String,
    confirmText: String = "",
    cancelText: String? = null,
    isCancellable: Boolean = true,
    onDismiss: () -> Unit = {},
    onClickConfirmed: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = isCancellable,
            dismissOnBackPress = isCancellable
        )
    ) {
        Card(
            modifier = modifier.widthIn(min = 280.dp, max = 400.dp),
            colors = CardDefaults.cardColors(
                containerColor = DongsaniTheme.colors.background.groupedElevated
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        style = DongsaniTheme.typos.bold.font16,
                        color = DongsaniTheme.colors.label.onBgPrimary
                    )
                }
                Text(
                    text = message,
                    style = DongsaniTheme.typos.regular.font14,
                    color = DongsaniTheme.colors.label.onBgPrimary
                )
                Row {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    if (cancelText != null) {
                        Button(
                            onClick = onDismiss
                        ) {
                            Text(
                                text = cancelText,
                                style = DongsaniTheme.typos.bold.font16,
                                color = DongsaniTheme.colors.label.onBgPrimary
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                    Button(
                        onClick = {
                            onClickConfirmed()
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Purple)
                    ) {
                        Text(
                            text = confirmText,
                            style = DongsaniTheme.typos.bold.font16,
                            color = DongsaniTheme.colors.system.white
                        )
                    }
                }
            }
        }
    }
}
