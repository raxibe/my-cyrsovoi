package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.FixedAsset.FixedAssetRepository
import com.example.cursova.RepairDocoment.RepairDocument
import com.example.cursova.RepairDocoment.RepairDocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RepairDocumentViewModel @Inject constructor(
    private val repairDocumentRepository: RepairDocumentRepository,
    private val fixedAssetRepository: FixedAssetRepository
) : ViewModel() {
    val repairDocuments = repairDocumentRepository.getAllRepairDocuments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    suspend fun getRepairDocumentById(id: Int): RepairDocument? {
        return repairDocumentRepository.getRepairDocumentById(id)
    }

    fun addRepairDocument(repairDocument: RepairDocument) {
        viewModelScope.launch {
            repairDocumentRepository.insertRepairDocument(repairDocument)
            fixedAssetRepository.updateFixedAssetStatus(repairDocument.fixedAssetId, "в ремонте")
        }
    }

    fun deleteRepairDocument(repairDocument: RepairDocument) {
        viewModelScope.launch {
            repairDocumentRepository.deleteRepairDocument(repairDocument)
        }
    }

    fun deleteAllRepairDocuments() {
        viewModelScope.launch {
            repairDocumentRepository.deleteAllRepairDocuments()
        }
    }

    suspend fun generateDocumentNumber(): String {
        val maxId = repairDocumentRepository.getMaxDocumentId() ?: 0
        return "РМ-${maxId + 1}-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}"
    }
}