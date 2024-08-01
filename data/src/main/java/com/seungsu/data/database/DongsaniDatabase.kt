package com.seungsu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seungsu.data.database.dao.ExerciseRecordDao
import com.seungsu.data.model.local.ExerciseRecordDbEntity

@Database(
    entities = [ExerciseRecordDbEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class DongsaniDatabase : RoomDatabase() {
    abstract fun exerciseRecordDao(): ExerciseRecordDao

    companion object {
        const val DB_NAME = "dongsani.db"
    }
}
