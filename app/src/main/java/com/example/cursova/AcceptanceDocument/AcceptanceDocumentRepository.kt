package com.example.cursova.AcceptanceDocument

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AcceptanceDocumentRepository @Inject constructor(
    private val acceptanceDocumentDao: AcceptanceDocumentDao
) {
    fun getAllAcceptanceDocuments(): Flow<List<AcceptanceDocument>> = acceptanceDocumentDao.getAllAcceptanceDocuments()

    suspend fun insertAcceptanceDocument(acceptanceDocument: AcceptanceDocument) = acceptanceDocumentDao.insertAcceptanceDocument(acceptanceDocument)

    suspend fun deleteAcceptanceDocument(acceptanceDocument: AcceptanceDocument) = acceptanceDocumentDao.deleteAcceptanceDocument(acceptanceDocument)

    suspend fun deleteAllAcceptanceDocuments() = acceptanceDocumentDao.deleteAllAcceptanceDocuments()

    suspend fun getMaxDocumentId(): Int? = acceptanceDocumentDao.getMaxDocumentId()
}