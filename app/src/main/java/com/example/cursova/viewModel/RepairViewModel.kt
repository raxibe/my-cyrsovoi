package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.repair.Repair
import com.example.cursova.repair.RepairRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepairViewModel @Inject constructor(
    private val repository: RepairRepository
) : ViewModel() {
    val repair = repository.getAllRepair()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addRepair(repair: Repair) {
        viewModelScope.launch {
            repository.insertRepair(repair)
        }
    }

    fun deleteRepair(repair: Repair) {
        viewModelScope.launch {
            repository.deleteRepair(repair)
        }
    }

    fun deleteAllRepair() {
        viewModelScope.launch {
            repository.deleteAllRepair()
        }
    }
}