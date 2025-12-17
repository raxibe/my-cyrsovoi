package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.Hall.Hall
import com.example.cursova.Hall.HallRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HallViewModel @Inject constructor(
    private val repository: HallRepository
) : ViewModel() {
    val halls = repository.getAllHalls()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addHall(hall: Hall) {
        viewModelScope.launch {
            repository.insertHall(hall)
        }
    }

    fun deleteHall(hall: Hall) {
        viewModelScope.launch {
            repository.deleteHall(hall)
        }
    }

    fun deleteAllHalls() {
        viewModelScope.launch {
            repository.deleteAllHalls()
        }
    }
}