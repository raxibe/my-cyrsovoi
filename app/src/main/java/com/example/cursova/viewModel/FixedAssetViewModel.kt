package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.FixedAsset.FixedAssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixedAssetViewModel @Inject constructor(
    private val repository: FixedAssetRepository
) : ViewModel() {
    val fixedAssets = repository.getAllFixedAssets()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addFixedAsset(fixedAsset: FixedAsset) {
        viewModelScope.launch {
            repository.insertFixedAsset(fixedAsset)
        }
    }

    fun updateFixedAsset(fixedAsset: FixedAsset) {
        viewModelScope.launch {
            repository.updateFixedAsset(fixedAsset)
        }
    }

    fun deleteFixedAsset(fixedAsset: FixedAsset) {
        viewModelScope.launch {
            repository.deleteFixedAsset(fixedAsset)
        }
    }

    fun deleteAllFixedAssets() {
        viewModelScope.launch {
            repository.deleteAllFixedAssets()
        }
    }

    fun updateFixedAssetStatus(id: Int, status: String) {
        viewModelScope.launch {
            repository.updateFixedAssetStatus(id, status)
        }
    }
}