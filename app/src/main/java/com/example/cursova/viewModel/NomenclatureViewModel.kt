package com.example.cursova.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursova.data.Nomenclature
import com.example.cursova.data.NomenclatureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class NomenclatureViewModel @Inject constructor(
//    private val repository: NomenclatureRepository
//) : ViewModel(){
//
//
//    // Поисковый запрос
//    private val _searchQuery = MutableStateFlow("")
//    val searchQuery = _searchQuery.asStateFlow()
//
//    // Выбранный курс для фильтрации
//    private val _selectedType = MutableStateFlow("Все")
//    val selectedType = _selectedType.asStateFlow()
//
//    // Список студентов с учетом поиска и фильтрации
//    val nomen = combine(
//        searchQuery,
//        selectedType
//    ) { query, type ->
//        Pair(query, type)
//    }.flatMapLatest { (query, type) ->
//        when {
//            query.isNotBlank() -> repository.searchNomenclature(query)
//            type != "Все" -> repository.getNomenclatureByType(type)
//            else -> repository.getAllNomenclature()
//        }
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = emptyList()
//    )
//
//    // Количество студентов
//    val nomenCount = repository.getNomenclatureCount()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = 0
//        )
//
//    init {
//        // Добавить тестовые данные при первом запуске
//        viewModelScope.launch {
//            repository.insertSampleData()
//        }
//    }
//
//    fun addNomen(nomen: Nomenclature) {
//        viewModelScope.launch {
//            repository.insertNomenclature(nomen)
//        }
//    }
//
//    fun deleteNomen(nomen: Nomenclature) {
//        viewModelScope.launch {
//            repository.deleteNomenclature(nomen)
//        }
//    }
//
//    fun deleteAllNomen() {
//        viewModelScope.launch {
//            repository.deleteAllNomenclature()
//        }
//    }
//}



@HiltViewModel
class NomenclatureViewModel @Inject constructor(
    private val repository: NomenclatureRepository
) : ViewModel() {
    val nomen = repository.getAllNomenclature()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addNomen(nomen: Nomenclature) {
        viewModelScope.launch {
            repository.insertNomenclature(nomen)
        }
    }

    fun deleteNomen(nomen: Nomenclature) {
        viewModelScope.launch {
            repository.deleteNomenclature(nomen)
        }
    }

    fun deleteAllNomen() {
        viewModelScope.launch {
            repository.deleteAllNomenclature()
        }
    }
}