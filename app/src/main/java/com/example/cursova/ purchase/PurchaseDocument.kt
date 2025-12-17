package com.example.cursova.purchase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_documents")
data class PurchaseDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val supplierName: String,
    val items: String, // JSON строка с товарами
    val documentNumber: String, // Номер документа
    val creationDate: Long // Дата создания (в миллисекундах)
)