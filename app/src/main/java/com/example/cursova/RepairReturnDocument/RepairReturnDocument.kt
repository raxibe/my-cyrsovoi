package com.example.cursova.RepairReturnDocument

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repair_return_documents")
data class RepairReturnDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val documentNumber: String,
    val creationDate: Long,
    val repairDocumentId: Int,
    val condition: String,
    val status: String = "возвращено"
)