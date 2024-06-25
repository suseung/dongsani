package com.seungsu.design.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun DongsaniTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    colors: TextFieldColors = TextFieldDefaults.colors(),
    fontSize: TextUnit = 16.sp,
    fontStyle: FontStyle = FontStyle.Normal,
    textStyle: TextStyle = TextStyle(),
    onValueChange: (String) -> Unit
) = TextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    readOnly = false,
    placeholder = placeholder,
    leadingIcon = null,
    trailingIcon = null,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
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
