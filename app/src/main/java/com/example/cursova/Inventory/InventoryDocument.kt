package com.example.cursova.Inventory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_documents")
data class InventoryDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val documentNumber: String,
    val creationDate: Long,
    val status: String = "создан"
)