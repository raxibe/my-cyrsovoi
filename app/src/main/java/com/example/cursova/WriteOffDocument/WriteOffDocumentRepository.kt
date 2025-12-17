package com.example.cursova.WriteOffDocument

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WriteOffDocumentRepository @Inject constructor(
    private val writeOffDocumentDao: WriteOffDocumentDao
) {
    fun getAllWriteOffDocuments(): Flow<List<WriteOffDocument>> = writeOffDocumentDao.getAllWriteOffDocuments()

    suspend fun insertWriteOffDocument(writeOffDocument: WriteOffDocument) = writeOffDocumentDao.insertWriteOffDocument(writeOffDocument)

    suspend fun deleteWriteOffDocument(writeOffDocument: WriteOffDocument) = writeOffDocumentDao.deleteWriteOffDocument(writeOffDocument)

    suspend fun deleteAllWriteOffDocuments() = writeOffDocumentDao.deleteAllWriteOffDocuments()

    suspend fun getMaxDocumentId(): Int? = writeOffDocumentDao.getMaxDocumentId()
}