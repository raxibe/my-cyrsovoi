package com.example.cursova.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NomenclatureRepository @Inject constructor(
    private val nomenDao: NomenclatureDao
) {
    fun getAllNomenclature(): Flow<List<Nomenclature>> {
        return nomenDao.getAllNomenclature()
    }


    fun searchNomenclature(query: String): Flow<List<Nomenclature>> {
        return nomenDao.searchNomenclature(query)
    }
    fun getNomenclatureByType(query: String): Flow<List<Nomenclature>> {
        return nomenDao.getNomenclatureByType(query)
    }

    fun getNomenclatureCount(): Flow<Int> {
        return nomenDao.getNomenclatureCount()
    }

    suspend fun insertNomenclature(student: Nomenclature): Long {
        return nomenDao.insertNomenclature(student)
    }


    suspend fun deleteNomenclature(student: Nomenclature) {
        nomenDao.deleteNomenclature(student)
    }

    suspend fun deleteAllNomenclature() {
        nomenDao.deleteAllNomenclature()
    }


//    // Добавить тестовые данные
    suspend fun insertSampleData() {
        if (nomenDao.getNomenclatureCount().equals(0)) {
            val sampleStudents = listOf(
                Nomenclature(
                    name = "Анна Иванова",
                    type = "22",
                    price = 12
                ),
                Nomenclature(
                    name = "Анна Иванова",
                    type = "22",
                    price = 12
                ),
                Nomenclature(
                    name = "Анна Иванова",
                    type = "22",
                    price = 12
                ),
                Nomenclature(
                    name = "Анна Иванова",
                    type = "22",
                    price = 12
                )
            )
            nomenDao.insertNomenclature(sampleStudents)
        }
    }

}