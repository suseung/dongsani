package com.seungsu.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ApiResult<out R> {
    data object Loading: ApiResult<Nothing>
    data class Error(val exception: Exception): ApiResult<Nothing>
    data class Success<out T>(val data: T): ApiResult<T>
}

fun <T> Flow<T>.asResult(): Flow<ApiResult<T>> {
    return this
        .map<T, ApiResult<T>> { ApiResult.Success(it) }
        .onStart { emit(ApiResult.Loading) }
        .catch { e -> emit(ApiResult.Error(e as? Exception ?: Exception(e))) }
}
