package com.example.cursova.Inventory

import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.FixedAsset.FixedAssetDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryDocumentRepository @Inject constructor(
    private val inventoryDocumentDao: InventoryDocumentDao,
    private val inventoryItemDao: InventoryItemDao,
    private val fixedAssetDao: FixedAssetDao
) {
    fun getAllInventoryDocuments(): Flow<List<InventoryDocument>> =
        inventoryDocumentDao.getAllInventoryDocuments()

    fun getInventoryItemsByDocumentId(inventoryDocumentId: Int): Flow<List<InventoryItem>> =
        inventoryItemDao.getInventoryItemsByDocumentId(inventoryDocumentId)

    suspend fun insertInventoryDocument(inventoryDocument: InventoryDocument) =
        inventoryDocumentDao.insertInventoryDocument(inventoryDocument)

    suspend fun insertInventoryItem(inventoryItem: InventoryItem) =
        inventoryItemDao.insertInventoryItem(inventoryItem)

    suspend fun updateInventoryItem(inventoryItem: InventoryItem) =
        inventoryItemDao.updateInventoryItem(inventoryItem)

    suspend fun deleteInventoryDocument(inventoryDocument: InventoryDocument) {
        inventoryItemDao.deleteAllInventoryItemsByDocumentId(inventoryDocument.id)
        inventoryDocumentDao.deleteInventoryDocument(inventoryDocument)
    }

    suspend fun deleteAllInventoryDocuments() =
        inventoryDocumentDao.deleteAllInventoryDocuments()

    suspend fun getMaxDocumentId(): Int? =
        inventoryDocumentDao.getMaxDocumentId()

    suspend fun getFixedAssets(): List<FixedAsset> {
        return fixedAssetDao.getAllFixedAssetsList()
    }
}