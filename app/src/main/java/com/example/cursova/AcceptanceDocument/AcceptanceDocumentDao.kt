package com.example.cursova.AcceptanceDocument

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AcceptanceDocumentDao {
    @Query("SELECT * FROM acceptance_documents")
    fun getAllAcceptanceDocuments(): Flow<List<AcceptanceDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcceptanceDocument(acceptanceDocument: AcceptanceDocument): Long

    @Delete
    suspend fun deleteAcceptanceDocument(acceptanceDocument: AcceptanceDocument)

    @Query("DELETE FROM acceptance_documents")
    suspend fun deleteAllAcceptanceDocuments()

    @Query("SELECT MAX(id) FROM acceptance_documents")
    suspend fun getMaxDocumentId(): Int?
}