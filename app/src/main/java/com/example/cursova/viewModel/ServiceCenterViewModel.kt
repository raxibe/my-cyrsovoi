package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.ServiceCenter.ServiceCenter
import com.example.cursova.ServiceCenter.ServiceCenterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceCenterViewModel @Inject constructor(
    private val repository: ServiceCenterRepository
) : ViewModel() {
    val serviceCenters = repository.getAllServiceCenters()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addServiceCenter(serviceCenter: ServiceCenter) {
        viewModelScope.launch {
            repository.insertServiceCenter(serviceCenter)
        }
    }

    fun deleteServiceCenter(serviceCenter: ServiceCenter) {
        viewModelScope.launch {
            repository.deleteServiceCenter(serviceCenter)
        }
    }

    fun deleteAllServiceCenters() {
        viewModelScope.launch {
            repository.deleteAllServiceCenters()
        }
    }
}