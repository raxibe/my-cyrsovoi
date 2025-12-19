package com.example.cursova.purchase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDocumentDao {

    @Query("SELECT * FROM purchase_documents ORDER BY id ASC")
    fun getAllPurchaseDocuments(): Flow<List<PurchaseDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchaseDocument(purchaseDocument: PurchaseDocument): Long

    @Delete
    suspend fun deletePurchaseDocument(purchaseDocument: PurchaseDocument)

    @Query("DELETE FROM purchase_documents")
    suspend fun deleteAllPurchaseDocuments()

    @Query("SELECT MAX(id) FROM purchase_documents")
    suspend fun getMaxDocumentId(): Int?
}