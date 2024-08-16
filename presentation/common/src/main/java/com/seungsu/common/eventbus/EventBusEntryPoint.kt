package com.seungsu.common.eventbus

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface EventBusEntryPoint {
    fun getEventBus(): EventBus

    companion object {
        fun resolve(context: Context): EventBusEntryPoint {
            val applicationContext = context.applicationContext ?: throw IllegalStateException()
            return EntryPointAccessors.fromApplication(
                applicationContext,
                EventBusEntryPoint::class.java
            )
        }
    }
}
