package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.purchase.PurchaseDocument
import com.example.cursova.purchase.PurchaseDocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PurchaseDocumentViewModel @Inject constructor(
    private val repository: PurchaseDocumentRepository
) : ViewModel() {
    val purchaseDocuments = repository.getAllPurchaseDocuments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addPurchaseDocument(purchaseDocument: PurchaseDocument) {
        viewModelScope.launch {
            repository.insertPurchaseDocument(purchaseDocument)
        }
    }

    fun deletePurchaseDocument(purchaseDocument: PurchaseDocument) {
        viewModelScope.launch {
            repository.deletePurchaseDocument(purchaseDocument)
        }
    }

    fun deleteAllPurchaseDocuments() {
        viewModelScope.launch {
            repository.deleteAllPurchaseDocuments()
        }
    }

    suspend fun generateDocumentNumber(): String {
        val maxId = repository.getMaxDocumentId() ?: 0
        return "ДОК-${maxId + 1}-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}"
    }
}