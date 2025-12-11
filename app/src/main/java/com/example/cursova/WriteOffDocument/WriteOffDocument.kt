package com.example.cursova.WriteOffDocument

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "write_off_documents")
data class WriteOffDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val documentNumber: String,
    val creationDate: Long,
    val fixedAssetId: Int, // ID основного средства
    val reason: String // Причина списания
)