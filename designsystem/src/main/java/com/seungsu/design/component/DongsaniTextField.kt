package com.seungsu.design.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.design.theme.DongsaniTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DongsaniTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = DongsaniTheme.colors.label.onBgPrimary
    ),
    fontSize: TextUnit = 16.sp,
    fontStyle: FontStyle = FontStyle.Normal,
    textStyle: TextStyle = TextStyle(),
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(4.dp),
                color = DongsaniTheme.colors.separator
            ),
        readOnly = false,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        singleLine = singleLine,
        maxLines = maxLines,
        isError = false,
        label = null,
        visualTransformation = VisualTransformation.None,
        textStyle = textStyle.copy(
            fontSize = fontSize,
            fontStyle = fontStyle
        ),
        colors = colors
    )
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun DongsaniTextFieldPreview() {
    DongsaniTheme {
        DongsaniTextField(
            modifier = Modifier.padding(16.dp),
            value = "Textfield",
            onValueChange = {}
        )
    }
}
