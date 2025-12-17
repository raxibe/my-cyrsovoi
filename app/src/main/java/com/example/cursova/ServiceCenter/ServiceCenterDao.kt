package com.example.cursova.ServiceCenter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceCenterDao {
    @Query("SELECT * FROM service_centers ORDER BY name ASC")
    fun getAllServiceCenters(): Flow<List<ServiceCenter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServiceCenter(serviceCenter: ServiceCenter): Long

    @Delete
    suspend fun deleteServiceCenter(serviceCenter: ServiceCenter)

    @Query("DELETE FROM service_centers")
    suspend fun deleteAllServiceCenters()
}