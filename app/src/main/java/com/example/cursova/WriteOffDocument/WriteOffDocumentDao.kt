package com.example.cursova.WriteOffDocument

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WriteOffDocumentDao {
    @Query("SELECT * FROM write_off_documents")
    fun getAllWriteOffDocuments(): Flow<List<WriteOffDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWriteOffDocument(writeOffDocument: WriteOffDocument): Long

    @Delete
    suspend fun deleteWriteOffDocument(writeOffDocument: WriteOffDocument)

    @Query("DELETE FROM write_off_documents")
    suspend fun deleteAllWriteOffDocuments()

    @Query("SELECT MAX(id) FROM write_off_documents")
    suspend fun getMaxDocumentId(): Int?
}