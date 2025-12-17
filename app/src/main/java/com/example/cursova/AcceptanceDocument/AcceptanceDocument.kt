package com.example.cursova.AcceptanceDocument

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "acceptance_documents")
data class AcceptanceDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val documentNumber: String, // Номер документа
    val creationDate: Long, // Дата создания
    val transferPerson: String, // Передающее лицо
    val responsibleId: Int, // ID материально-ответственного лица
    val items: String // JSON строка с товарами (или связь с Item)
)