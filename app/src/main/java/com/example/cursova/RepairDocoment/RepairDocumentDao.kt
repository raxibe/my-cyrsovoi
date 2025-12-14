package com.example.cursova.RepairDocoment

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepairDocumentDao {
    @Query("SELECT * FROM repair_documents")
    fun getAllRepairDocuments(): Flow<List<RepairDocument>>

//    @Query("SELECT * FROM repair_documents WHERE id = :id")
//    fun getRepairDocumentById(id: Int): Flow<RepairDocument?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepairDocument(repairDocument: RepairDocument): Long

    @Delete
    suspend fun deleteRepairDocument(repairDocument: RepairDocument)

    @Query("DELETE FROM repair_documents")
    suspend fun deleteAllRepairDocuments()

    @Query("SELECT MAX(id) FROM repair_documents")
    suspend fun getMaxDocumentId(): Int?

    @Query("SELECT * FROM repair_documents WHERE id = :id")
    suspend fun getRepairDocumentById(id: Int): RepairDocument?
}
