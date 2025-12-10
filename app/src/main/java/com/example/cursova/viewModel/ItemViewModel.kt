package com.example.cursova.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.purchase.Item
import com.example.cursova.purchase.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {
    val items = itemRepository.getAllItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addItem(item: Item) {
        viewModelScope.launch {
            Log.d("ItemViewModel", "Adding item: $item")
            itemRepository.insertItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            Log.d("ItemViewModel", "Updating item: $item")
            itemRepository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemRepository.deleteItem(item)
        }
    }

    suspend fun getItemById(itemId: Int): Item? {
        return itemRepository.getItemById(itemId)
    }




}