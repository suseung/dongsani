package com.seungsu.common.di

import com.seungsu.common.eventbus.EventBus
import com.seungsu.common.eventbus.EventBusImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class EventBusModule {
    @Binds
    @Singleton
    abstract fun bindsEventBus(eventBusImpl: EventBusImpl): EventBus
}
