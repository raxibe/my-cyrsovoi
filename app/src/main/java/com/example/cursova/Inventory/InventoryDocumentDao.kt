package com.example.cursova.Inventory

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDocumentDao {
    @Query("SELECT * FROM inventory_documents")
    fun getAllInventoryDocuments(): Flow<List<InventoryDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryDocument(inventoryDocument: InventoryDocument): Long

    @Delete
    suspend fun deleteInventoryDocument(inventoryDocument: InventoryDocument)

    @Query("DELETE FROM inventory_documents")
    suspend fun deleteAllInventoryDocuments()

    @Query("SELECT MAX(id) FROM inventory_documents")
    suspend fun getMaxDocumentId(): Int?
}