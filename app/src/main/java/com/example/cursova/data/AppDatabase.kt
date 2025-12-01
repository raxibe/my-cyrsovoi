package com.example.cursova.data

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(
    entities = [Nomenclature::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun nomentDao(): NomenclatureDao

    companion object {
        const val DATABASE_NAME = "app_database"
    }
}