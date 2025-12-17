package com.example.cursova.Responsible

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResponsibleRepository @Inject constructor(
    private val responsibleDao: ResponsibleDao
) {
    fun getAllResponsibles(): Flow<List<Responsible>> {
        return responsibleDao.getAllResponsibles()
    }

    suspend fun insertResponsible(responsible: Responsible): Long {
        return responsibleDao.insertResponsible(responsible)
    }

    suspend fun deleteResponsible(responsible: Responsible) {
        responsibleDao.deleteResponsible(responsible)
    }

    suspend fun deleteAllResponsibles() {
        responsibleDao.deleteAllResponsibles()
    }
}