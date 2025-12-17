package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.FixedAsset.FixedAssetRepository
import com.example.cursova.RepairDocoment.RepairDocumentRepository
import com.example.cursova.RepairReturnDocument.RepairReturnDocument
import com.example.cursova.RepairReturnDocument.RepairReturnDocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RepairReturnDocumentViewModel @Inject constructor(
    private val repairReturnDocumentRepository: RepairReturnDocumentRepository,
    private val repairDocumentRepository: RepairDocumentRepository,
    private val fixedAssetRepository: FixedAssetRepository
) : ViewModel() {
    val repairReturnDocuments = repairReturnDocumentRepository.getAllRepairReturnDocuments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addRepairReturnDocument(repairReturnDocument: RepairReturnDocument) {
        viewModelScope.launch {
            repairReturnDocumentRepository.insertRepairReturnDocument(repairReturnDocument)

            // Получаем ID основного средства из документа ремонта
            val repairDocument = repairDocumentRepository.getRepairDocumentById(repairReturnDocument.repairDocumentId)

            // Обновляем статус основного средства на "в зале"
            if (repairDocument != null) {
                fixedAssetRepository.updateFixedAssetStatus(repairDocument.fixedAssetId, "в зале")
            }
        }

    }

    fun deleteRepairReturnDocument(repairReturnDocument: RepairReturnDocument) {
        viewModelScope.launch {
            repairReturnDocumentRepository.deleteRepairReturnDocument(repairReturnDocument)
        }
    }

    suspend fun generateDocumentNumber(): String {
        val maxId = repairReturnDocumentRepository.getMaxDocumentId() ?: 0
        return "ВР-${maxId + 1}-${SimpleDateFormat("yyyy", Locale.getDefault()).format(Date())}"
    }
}

