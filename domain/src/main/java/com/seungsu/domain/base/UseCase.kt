package com.seungsu.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class FlowUseCase<in Params, Type>(private val dispatcher: CoroutineDispatcher) {
    operator fun invoke(params: Params): Flow<Type> {
        return execute(params).flowOn(dispatcher)
    }

    abstract fun execute(params: Params): Flow<Type>
}

abstract class UseCase<in Params, out Type>(private val dispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(params: Params): ApiResult<Type> {
        return try {
            withContext(dispatcher) {
                ApiResult.Success(execute(params))
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: Params): Type
}
