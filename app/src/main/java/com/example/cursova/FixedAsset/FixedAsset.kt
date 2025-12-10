package com.example.cursova.FixedAsset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fixed_assets")
data class FixedAsset(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String, // Наименование оборудования
    val inventoryNumber: String, // Инвентарный номер
    val responsibleId: Int, // ID материально-ответственного лица
    val acceptanceDocumentId: Int, // ID документа принятия к учету
    val status: String = "в зале" // Состояние инвентаря: "в зале", "в ремонте", "непригодно в использовании", "списано"
)