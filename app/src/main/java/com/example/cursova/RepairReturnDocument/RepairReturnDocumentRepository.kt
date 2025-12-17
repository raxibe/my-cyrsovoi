package com.example.cursova.RepairReturnDocument

import com.example.cursova.FixedAsset.FixedAssetDao
import com.example.cursova.RepairDocoment.RepairDocumentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepairReturnDocumentRepository @Inject constructor(
    private val repairReturnDocumentDao: RepairReturnDocumentDao,
    private val repairDocumentDao: RepairDocumentDao,
    private val fixedAssetDao: FixedAssetDao
) {
    fun getAllRepairReturnDocuments(): Flow<List<RepairReturnDocument>> =
        repairReturnDocumentDao.getAllRepairReturnDocuments()

    suspend fun insertRepairReturnDocument(repairReturnDocument: RepairReturnDocument) =
        repairReturnDocumentDao.insertRepairReturnDocument(repairReturnDocument)

    suspend fun deleteRepairReturnDocument(repairReturnDocument: RepairReturnDocument) =
        repairReturnDocumentDao.deleteRepairReturnDocument(repairReturnDocument)

    suspend fun deleteAllRepairReturnDocuments() =
        repairReturnDocumentDao.deleteAllRepairReturnDocuments()

    suspend fun getMaxDocumentId(): Int? =
        repairReturnDocumentDao.getMaxDocumentId()
}
