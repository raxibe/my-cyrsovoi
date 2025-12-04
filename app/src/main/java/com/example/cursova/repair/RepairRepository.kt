package com.example.cursova.repair

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepairRepository @Inject constructor(
    private val repairDao: RepairDao
) {
    fun getAllRepair(): Flow<List<Repair>> {
        return repairDao.getAllRepair()
    }

    suspend fun insertRepair(repair: Repair): Long {
        return repairDao.insertRepair(repair)
    }

    suspend fun deleteRepair(repair: Repair) {
        repairDao.deleteRepair(repair)
    }

    suspend fun deleteAllRepair() {
        repairDao.deleteAllRepair()
    }
}