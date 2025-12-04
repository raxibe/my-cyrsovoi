package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.Responsible.Responsible
import com.example.cursova.Responsible.ResponsibleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResponsibleViewModel @Inject constructor(
    private val repository: ResponsibleRepository
) : ViewModel() {
    val responsibles = repository.getAllResponsibles()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addResponsible(responsible: Responsible) {
        viewModelScope.launch {
            repository.insertResponsible(responsible)
        }
    }

    fun deleteResponsible(responsible: Responsible) {
        viewModelScope.launch {
            repository.deleteResponsible(responsible)
        }
    }

    fun deleteAllResponsibles() {
        viewModelScope.launch {
            repository.deleteAllResponsibles()
        }
    }
}