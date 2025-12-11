package com.example.cursova.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val Migration_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `write_off_documents` (\n" +
                    "                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "                        `documentNumber` TEXT NOT NULL,\n" +
                    "                        `creationDate` INTEGER NOT NULL,\n" +
                    "                        `fixedAssetId` INTEGER NOT NULL," +
                    "                `reason` TEXT NOT NULL\n" +
                    "                    )"
        )
    }

}
    
