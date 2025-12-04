package com.example.cursova.Supplier

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SupplierRepository @Inject constructor(
    private val supplierDao: SupplierDao
) {
    fun getAllSuppliers(): Flow<List<Supplier>> {
        return supplierDao.getAllSuppliers()
    }

    suspend fun insertSupplier(supplier: Supplier): Long {
        return supplierDao.insertSupplier(supplier)
    }

    suspend fun deleteSupplier(supplier: Supplier) {
        supplierDao.deleteSupplier(supplier)
    }

    suspend fun deleteAllSuppliers() {
        supplierDao.deleteAllSuppliers()
    }
}