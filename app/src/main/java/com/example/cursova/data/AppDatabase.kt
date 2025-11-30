package com.example.cursova.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Nomenclature::class],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun nomenclatureDao(): NomenclatureDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}