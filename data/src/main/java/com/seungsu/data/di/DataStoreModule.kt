package com.seungsu.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.seungsu.data.local.PreferenceStorageImpl
import com.seungsu.domain.preference.PreferenceStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    private val Context.prefDataStore by preferencesDataStore(
        name = "settings"
    )

    @Singleton
    @Provides
    fun provideSettingDataStore(
        @ApplicationContext context: Context
    ): PreferenceStorage = PreferenceStorageImpl(
        prefDatastore = context.prefDataStore
    )
}