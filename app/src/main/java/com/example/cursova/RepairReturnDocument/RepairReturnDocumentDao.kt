package com.example.cursova.RepairReturnDocument

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepairReturnDocumentDao {
    @Query("SELECT * FROM repair_return_documents")
    fun getAllRepairReturnDocuments(): Flow<List<RepairReturnDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepairReturnDocument(repairReturnDocument: RepairReturnDocument): Long

    @Delete
    suspend fun deleteRepairReturnDocument(repairReturnDocument: RepairReturnDocument)

    @Query("DELETE FROM repair_return_documents")
    suspend fun deleteAllRepairReturnDocuments()

    @Query("SELECT MAX(id) FROM repair_return_documents")
    suspend fun getMaxDocumentId(): Int?
}