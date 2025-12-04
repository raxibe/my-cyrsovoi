package com.example.cursova.repair

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepairDao {
    @Query("SELECT * FROM repair ORDER BY name ASC")
    fun getAllRepair(): Flow<List<Repair>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepair(repair: Repair): Long

    @Delete
    suspend fun deleteRepair(repair: Repair)

    @Query("DELETE FROM repair")
    suspend fun deleteAllRepair()
}