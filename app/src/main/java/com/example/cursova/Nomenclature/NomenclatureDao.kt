package com.example.cursova.Nomenclature

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NomenclatureDao {

        @Query("SELECT * FROM nomenclature ORDER BY name ASC")
        fun getAllNomenclature(): Flow<List<Nomenclature>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNomenclature(nomen: Nomenclature): Long

        @Delete
        suspend fun deleteNomenclature(nomen: Nomenclature)

        @Query("DELETE FROM nomenclature")
        suspend fun deleteAllNomenclature()


}