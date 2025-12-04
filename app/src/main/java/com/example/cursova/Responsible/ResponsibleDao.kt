package com.example.cursova.Responsible

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ResponsibleDao {
    @Query("SELECT * FROM responsibles ORDER BY name ASC")
    fun getAllResponsibles(): Flow<List<Responsible>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponsible(responsible: Responsible): Long

    @Delete
    suspend fun deleteResponsible(responsible: Responsible)

    @Query("DELETE FROM responsibles")
    suspend fun deleteAllResponsibles()
}