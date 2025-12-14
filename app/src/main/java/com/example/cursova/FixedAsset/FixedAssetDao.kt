package com.example.cursova.FixedAsset

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FixedAssetDao {
    @Query("SELECT * FROM fixed_assets")
    fun getAllFixedAssets(): Flow<List<FixedAsset>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFixedAsset(fixedAsset: FixedAsset): Long

    @Update
    suspend fun updateFixedAsset(fixedAsset: FixedAsset)

    @Delete
    suspend fun deleteFixedAsset(fixedAsset: FixedAsset)

    @Query("DELETE FROM fixed_assets")
    suspend fun deleteAllFixedAssets()

    @Query("UPDATE fixed_assets SET status = :status WHERE id = :id")
    suspend fun updateFixedAssetStatus(id: Int, status: String)




}