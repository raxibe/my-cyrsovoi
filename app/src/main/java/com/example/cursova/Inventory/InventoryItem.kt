package com.example.cursova.Inventory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val inventoryDocumentId: Int,
    val fixedAssetId: Int,
    val isPresent: Boolean = false
)