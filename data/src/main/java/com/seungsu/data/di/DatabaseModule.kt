package com.seungsu.data.di

import android.content.Context
import androidx.room.Room
import com.seungsu.data.database.DongsaniDatabase
import com.seungsu.data.database.dao.ExerciseRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DongsaniDatabase {
        return Room.databaseBuilder(
            context,
            DongsaniDatabase::class.java,
            DongsaniDatabase.DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideExerciseRecordDao(database: DongsaniDatabase): ExerciseRecordDao {
        return database.exerciseRecordDao()
    }
}
