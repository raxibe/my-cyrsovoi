package com.example.cursova.RepairDocoment

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepairDocumentRepository @Inject constructor(
    private val repairDocumentDao: RepairDocumentDao
) {
    fun getAllRepairDocuments(): Flow<List<RepairDocument>> =
        repairDocumentDao.getAllRepairDocuments()

    suspend fun getRepairDocumentById(id: Int): RepairDocument? {
        return repairDocumentDao.getRepairDocumentById(id)
    }

    suspend fun insertRepairDocument(repairDocument: RepairDocument) =
        repairDocumentDao.insertRepairDocument(repairDocument)

    suspend fun deleteRepairDocument(repairDocument: RepairDocument) =
        repairDocumentDao.deleteRepairDocument(repairDocument)

    suspend fun deleteAllRepairDocuments() =
        repairDocumentDao.deleteAllRepairDocuments()

    suspend fun getMaxDocumentId(): Int? =
        repairDocumentDao.getMaxDocumentId()
}