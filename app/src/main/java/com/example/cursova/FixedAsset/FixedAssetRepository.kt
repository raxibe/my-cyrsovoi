package com.example.cursova.FixedAsset

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FixedAssetRepository @Inject constructor(
    private val fixedAssetDao: FixedAssetDao
) {
    fun getAllFixedAssets(): Flow<List<FixedAsset>> = fixedAssetDao.getAllFixedAssets()

    suspend fun insertFixedAsset(fixedAsset: FixedAsset) = fixedAssetDao.insertFixedAsset(fixedAsset)

    suspend fun updateFixedAsset(fixedAsset: FixedAsset) = fixedAssetDao.updateFixedAsset(fixedAsset)

    suspend fun deleteFixedAsset(fixedAsset: FixedAsset) = fixedAssetDao.deleteFixedAsset(fixedAsset)

    suspend fun deleteAllFixedAssets() = fixedAssetDao.deleteAllFixedAssets()

    suspend fun getAllFixedAssetsList(): List<FixedAsset> = fixedAssetDao.getAllFixedAssetsList()

    suspend fun updateFixedAssetStatus(id: Int, status: String) = fixedAssetDao.updateFixedAssetStatus(id, status)
}