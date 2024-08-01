package com.seungsu.data.di

import com.seungsu.data.repository.ExerciseRecordRepository.ExerciseRecordLocalDataSourceImpl
import com.seungsu.data.repository.ExerciseRecordRepository.remote.ExerciseRecordLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindExerciseRecordLocalDataSource(
        exerciseRecordLocalDataSourceImpl: ExerciseRecordLocalDataSourceImpl
    ): ExerciseRecordLocalDataSource
}
