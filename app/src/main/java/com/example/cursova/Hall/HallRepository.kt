package com.example.cursova.Hall

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HallRepository @Inject constructor(
    private val hallDao: HallDao
) {
    fun getAllHalls(): Flow<List<Hall>> {
        return hallDao.getAllHalls()
    }

    suspend fun insertHall(hall: Hall): Long {
        return hallDao.insertHall(hall)
    }

    suspend fun deleteHall(hall: Hall) {
        hallDao.deleteHall(hall)
    }

    suspend fun deleteAllHalls() {
        hallDao.deleteAllHalls()
    }
}