package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.AcceptanceDocument.AcceptanceDocument
import com.example.cursova.AcceptanceDocument.AcceptanceDocumentRepository
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.FixedAsset.FixedAssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AcceptanceDocumentViewModel @Inject constructor(
    private val repository: AcceptanceDocumentRepository,
    private val fixedAssetRepository: FixedAssetRepository
) : ViewModel() {
    val acceptanceDocuments = repository.getAllAcceptanceDocuments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addAcceptanceDocument(
        acceptanceDocument: AcceptanceDocument,
        fixedAsset: FixedAsset
    ) {
        viewModelScope.launch {
            val documentId = repository.insertAcceptanceDocument(acceptanceDocument)
            fixedAssetRepository.insertFixedAsset(fixedAsset.copy(acceptanceDocumentId = documentId.toInt()))
        }
    }

    fun deleteAcceptanceDocument(acceptanceDocument: AcceptanceDocument) {
        viewModelScope.launch {
            repository.deleteAcceptanceDocument(acceptanceDocument)
        }
    }

    fun deleteAllAcceptanceDocuments() {
        viewModelScope.launch {
            repository.deleteAllAcceptanceDocuments()
        }
    }

    suspend fun generateDocumentNumber(): String {
        val maxId = repository.getMaxDocumentId() ?: 0
        return "ПКУ-${maxId + 1}-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}"
    }
}