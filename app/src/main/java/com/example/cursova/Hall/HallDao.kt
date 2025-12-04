package com.example.cursova.Hall

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HallDao {
    @Query("SELECT * FROM halls ORDER BY name ASC")
    fun getAllHalls(): Flow<List<Hall>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHall(hall: Hall): Long

    @Delete
    suspend fun deleteHall(hall: Hall)

    @Query("DELETE FROM halls")
    suspend fun deleteAllHalls()
}