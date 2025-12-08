package com.example.cursova.purchase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PurchaseDocumentRepository @Inject constructor(
    private val purchaseDocumentDao: PurchaseDocumentDao
) {
    fun getAllPurchaseDocuments(): Flow<List<PurchaseDocument>> {
        return purchaseDocumentDao.getAllPurchaseDocuments()
    }

    suspend fun insertPurchaseDocument(purchaseDocument: PurchaseDocument): Long {
        return purchaseDocumentDao.insertPurchaseDocument(purchaseDocument)
    }

    suspend fun deletePurchaseDocument(purchaseDocument: PurchaseDocument) {
        purchaseDocumentDao.deletePurchaseDocument(purchaseDocument)
    }

    suspend fun deleteAllPurchaseDocuments() {
        purchaseDocumentDao.deleteAllPurchaseDocuments()
    }
}