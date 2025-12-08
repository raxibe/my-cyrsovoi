package com.example.cursova.purchase



data class Item(
    var name: String,
    var quantity: Int,
    var price: Double
) {
    override fun toString(): String {
        return "Товар: $name, Количество: $quantity, Цена: $price"
    }
}