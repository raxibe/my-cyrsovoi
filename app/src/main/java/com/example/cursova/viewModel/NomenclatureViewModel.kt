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

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val repository: NomenclatureRepository
) : ViewModel(){


    // Поисковый запрос
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    // Выбранный курс для фильтрации
    private val _selectedCourse = MutableStateFlow("Все")
    val selectedCourse = _selectedCourse.asStateFlow()

//    // Список студентов с учетом поиска и фильтрации
    val nomens = combine(
        searchQuery,
        selectedCourse
    ) { query, course ->
        Pair(query, course)
    }.flatMapLatest { (query, course) ->
        when {
            query.isNotBlank() -> repository.searchStudents(query)
            course != "Все" -> repository.getStudentsByCourse(course)
            else -> repository.getAllStudents()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Количество студентов
    val nomenclatureCount = repository.getStudentCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    init {
        // Добавить тестовые данные при первом запуске
        viewModelScope.launch {
            repository.insertSampleData()
        }
    }

    fun addNomenclature(student: Nomenclature) {
        viewModelScope.launch {
            repository.insertNomenclature(student)
        }
    }

    fun deleteNomenclature(student: Nomenclature) {
        viewModelScope.launch {
            repository.deleteNomenclature(student)
        }
    }

    fun deleteAllNomenclature() {
        viewModelScope.launch {
            repository.deleteAllNomenclature()
        }
    }
}