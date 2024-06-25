package com.seungsu.common.ext

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle

fun AnnotatedString.Builder.addStyleSafely(
    style: SpanStyle,
    start: Int,
    end: Int
) = addStyle(style, start, end.takeIf { it <= length } ?: length)
