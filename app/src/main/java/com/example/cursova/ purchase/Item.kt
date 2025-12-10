package com.example.cursova.purchase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val quantity: Int,
    val price: Double
) {
    override fun toString(): String {
        return "Товар: $name, Количество: $quantity, Цена: $price"
    }
}