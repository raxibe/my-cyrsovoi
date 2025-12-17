package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.FixedAsset.FixedAssetRepository
import com.example.cursova.Inventory.InventoryDocument
import com.example.cursova.Inventory.InventoryDocumentDao
import com.example.cursova.Inventory.InventoryDocumentRepository
import com.example.cursova.Inventory.InventoryItem
import com.example.cursova.Inventory.InventoryItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val inventoryDocumentRepository: InventoryDocumentRepository,
    private val fixedAssetRepository: FixedAssetRepository,
    private val inventoryDocumentDao: InventoryDocumentDao,
    private val inventoryItemDao: InventoryItemDao
) : ViewModel() {
    val inventoryDocuments = inventoryDocumentRepository.getAllInventoryDocuments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

//    fun getInventoryItemsByDocumentId(inventoryDocumentId: Int): Flow<List<InventoryItem>> =
//        inventoryDocumentRepository.getInventoryItemsByDocumentId(inventoryDocumentId)
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(5000),
//                initialValue = emptyList()
//            )

    fun addInventoryDocument(inventoryDocument: InventoryDocument) {
        viewModelScope.launch {
            inventoryDocumentRepository.insertInventoryDocument(inventoryDocument)
        }
    }

    fun addInventoryItems(inventoryItems: List<InventoryItem>) {
        viewModelScope.launch {
            inventoryItems.forEach { inventoryItem ->
                inventoryDocumentRepository.insertInventoryItem(inventoryItem)
            }
        }
    }

    fun updateInventoryItem(inventoryItem: InventoryItem) {
        viewModelScope.launch {
            inventoryDocumentRepository.updateInventoryItem(inventoryItem)
        }
    }

    fun deleteInventoryDocument(inventoryDocument: InventoryDocument) {
        viewModelScope.launch {
            inventoryDocumentRepository.deleteInventoryDocument(inventoryDocument)
        }
    }

    suspend fun generateDocumentNumber(): String {
        val maxId = inventoryDocumentRepository.getMaxDocumentId() ?: 0
        return "ИН-${maxId + 1}-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}"
    }

    suspend fun getFixedAssets(): List<FixedAsset> {
        return inventoryDocumentRepository.getFixedAssets()
    }

    suspend fun getMaxDocumentId(): Int? {
        return inventoryDocumentRepository.getMaxDocumentId()
    }

    val fixedAssets: Flow<List<FixedAsset>> = fixedAssetRepository.getAllFixedAssets()


    suspend fun getInventoryDocumentById(id: Int): InventoryDocument? {
        return withContext(Dispatchers.IO) {
            inventoryDocumentDao.getInventoryDocumentById(id)
        }
    }

    fun getInventoryItemsByDocumentId(documentId: Int): Flow<List<InventoryItem>> {
        return inventoryItemDao.getInventoryItemsByDocumentId(documentId)
    }



}