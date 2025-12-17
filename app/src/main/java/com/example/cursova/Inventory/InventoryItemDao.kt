package com.example.cursova.Inventory

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryItemDao {
    @Query("SELECT * FROM inventory_items WHERE inventoryDocumentId = :inventoryDocumentId")
    fun getInventoryItemsByDocumentId(inventoryDocumentId: Int): Flow<List<InventoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryItem(inventoryItem: InventoryItem): Long

    @Update
    suspend fun updateInventoryItem(inventoryItem: InventoryItem)

    @Delete
    suspend fun deleteInventoryItem(inventoryItem: InventoryItem)

    @Query("DELETE FROM inventory_items WHERE inventoryDocumentId = :inventoryDocumentId")
    suspend fun deleteAllInventoryItemsByDocumentId(inventoryDocumentId: Int)


}