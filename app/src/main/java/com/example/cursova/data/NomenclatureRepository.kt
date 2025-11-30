package com.example.cursova.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NomenclatureRepository @Inject constructor(
    private val NomenclatureDao: NomenclatureDao
) {
    fun getAllStudents(): Flow<List<Nomenclature>> {
        return NomenclatureDao.getAllNomenclature()
    }


    fun searchStudents(query: String): Flow<List<Nomenclature>> {
        return NomenclatureDao.searchNomenclature(query)
    }
    fun getStudentsByCourse(query: String): Flow<List<Nomenclature>> {
        return NomenclatureDao.getNomenclatureByCourse(query)
    }

    fun getStudentCount(): Flow<Int> {
        return NomenclatureDao.getNomenclatureCount()
    }

    suspend fun insertNomenclature(student: Nomenclature): Long {
        return NomenclatureDao.insertNomenclature(student)
    }

    suspend fun deleteNomenclature(student: Nomenclature) {
        NomenclatureDao.deleteNomenclature(student)
    }

    suspend fun deleteAllNomenclature() {
        NomenclatureDao.deleteAllNomenclature()
    }

    suspend fun insertSampleData() {
        if (NomenclatureDao.getNomenclatureCount().equals(0)) {
            val sampleNomenclature = listOf(
                Nomenclature(
                    name = "Беговая дорожка",
                    type = "Инвентарь"

                    ),
                Nomenclature(
                    name = "Тренажер для жима ногами ",
                    type = "Инвентарь"

                    ),
                Nomenclature(
                    name = "Тренажер для задних дельт",
                    type = "Инвентарь"

                    ),
                Nomenclature(
                    name = "Тренажер машина Смита",
                    type = "Инвентарь"

                    ),
                Nomenclature(
                    name = "Тренажер сгибания ног",
                    type = "Инвентарь"

                    ),
                Nomenclature(
                    name = "Штанга",
                    type = "Материал"

                    ),
            )
            NomenclatureDao.insertNomenclature(sampleNomenclature)
        }
    }


}