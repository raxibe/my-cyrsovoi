package com.example.cursova.purchase

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
) {
    fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    suspend fun updateItem(item: Item) = itemDao.updateItem(item)

    suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)

    suspend fun deleteAllItems() = itemDao.deleteAllItems()

    suspend fun getItemById(itemId: Int): Item? = itemDao.getItemById(itemId)
}