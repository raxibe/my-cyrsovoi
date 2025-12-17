package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.FixedAsset.FixedAssetRepository
import com.example.cursova.WriteOffDocument.WriteOffDocument
import com.example.cursova.WriteOffDocument.WriteOffDocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class WriteOffDocumentViewModel @Inject constructor(
    private val repository: WriteOffDocumentRepository,
    private val fixedAssetRepository: FixedAssetRepository
) : ViewModel() {
    val writeOffDocuments = repository.getAllWriteOffDocuments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addWriteOffDocument(writeOffDocument: WriteOffDocument) {
        viewModelScope.launch {
            try {
                repository.insertWriteOffDocument(writeOffDocument)
                fixedAssetRepository.updateFixedAssetStatus(writeOffDocument.fixedAssetId, "списано")
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }

    fun deleteWriteOffDocument(writeOffDocument: WriteOffDocument) {
        viewModelScope.launch {
            repository.deleteWriteOffDocument(writeOffDocument)
        }
    }

    fun deleteAllWriteOffDocuments() {
        viewModelScope.launch {
            repository.deleteAllWriteOffDocuments()
        }
    }

    suspend fun generateDocumentNumber(): String {
        val maxId = repository.getMaxDocumentId() ?: 0
        return "СП-${maxId + 1}-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}"
    }
}