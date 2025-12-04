package com.example.cursova.ServiceCenter

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceCenterRepository @Inject constructor(
    private val serviceCenterDao: ServiceCenterDao
) {
    fun getAllServiceCenters(): Flow<List<ServiceCenter>> {
        return serviceCenterDao.getAllServiceCenters()
    }

    suspend fun insertServiceCenter(serviceCenter: ServiceCenter): Long {
        return serviceCenterDao.insertServiceCenter(serviceCenter)
    }

    suspend fun deleteServiceCenter(serviceCenter: ServiceCenter) {
        serviceCenterDao.deleteServiceCenter(serviceCenter)
    }

    suspend fun deleteAllServiceCenters() {
        serviceCenterDao.deleteAllServiceCenters()
    }
}