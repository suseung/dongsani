package com.seungsu.data.di

import com.seungsu.data.repository.ExerciseRecordRepository.ExerciseRecordRepositoryImpl
import com.seungsu.domain.repository.ExerciseRecordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsExerciseRecordRepository(
        recordsRepositoryImpl: ExerciseRecordRepositoryImpl
    ): ExerciseRecordRepository
}
