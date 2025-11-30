package com.example.cursova.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NomenclatureDao {

    @Query("SELECT * FROM nomenclature ORDER BY name ASC")
    fun getAllNomenclature(): Flow<List<Nomenclature>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNomenclature(nomenclature: Nomenclature): Long

    // Добавить список студентов
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNomenclature(nomenclature: List<Nomenclature>)

    // Поиск студентов по имени
    @Query("SELECT * FROM nomenclature WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchNomenclature(searchQuery: String): Flow<List<Nomenclature>>

//     Получить студентов по курсу
    @Query("SELECT * FROM nomenclature WHERE type = :course")
    fun getNomenclatureByCourse(course: String): Flow<List<Nomenclature>>

    // Получить количество студентов
    @Query("SELECT COUNT(*) FROM nomenclature")
    fun getNomenclatureCount(): Flow<Int>

    @Delete
    suspend fun deleteNomenclature(nomenclature: Nomenclature)

    // Удалить всех студентов
    @Query("DELETE FROM nomenclature")
    suspend fun deleteAllNomenclature()
}