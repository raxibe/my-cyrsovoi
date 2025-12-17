package com.example.cursova.RepairDocoment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repair_documents")
data class RepairDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val documentNumber: String,
    val creationDate: Long,
    val fixedAssetId: Int,
    val serviceCenterId: Int,
    val repairTypeId: Int,
    val repairCost: Double,
    val repairDuration: Int, // Срок ремонта в днях
    val status: String = "в ремонте"
) {

}