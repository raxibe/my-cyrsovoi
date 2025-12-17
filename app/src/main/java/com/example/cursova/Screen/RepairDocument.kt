package com.example.cursova.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.RepairDocoment.RepairDocument
import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.RepairDocumentViewModel
import com.example.cursova.viewModel.RepairViewModel
import com.example.cursova.viewModel.ServiceCenterViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepairDocument(
    navController: NavController,
    repairDocumentViewModel: RepairDocumentViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel(),
    serviceCenterViewModel: ServiceCenterViewModel = hiltViewModel(),
    repairViewModel: RepairViewModel = hiltViewModel()
) {
    val repairDocuments by repairDocumentViewModel.repairDocuments.collectAsStateWithLifecycle()
    val fixedAssets by fixedAssetViewModel.fixedAssets.collectAsStateWithLifecycle()
    val serviceCenters by serviceCenterViewModel.serviceCenters.collectAsStateWithLifecycle()
    val repairTypes by repairViewModel.repair.collectAsStateWithLifecycle()

    // Фильтруем и сортируем активные документы ремонта по дате создания в обратном порядке
    val activeRepairDocuments = remember(repairDocuments) {
        repairDocuments
            .filter { it.status == "в ремонте" }
            .sortedByDescending { it.creationDate }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Документы сдачи в ремонт ремонта") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.фонпервогоэкрана)
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.AddRepairDocument.route) }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.фонпервогоэкрана))
                .padding(paddingValues)
        ) {
            if (activeRepairDocuments.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Нет активных документов ремонта")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(activeRepairDocuments) { document ->
                        val fixedAsset = fixedAssets.find { it.id == document.fixedAssetId }
                        val serviceCenter = serviceCenters.find { it.id == document.serviceCenterId }
                        val repairType = repairTypes.find { it.id == document.repairTypeId }

                        RepairDocumentCard(
                            repairDocument = document,
                            fixedAssetName = fixedAsset?.name ?: "",
                            inventoryNumber = fixedAsset?.inventoryNumber ?: "",
                            serviceCenterName = serviceCenter?.name ?: "",
                            repairTypeName = repairType?.name ?: "",
                            onDeleteClick = { repairDocumentViewModel.deleteRepairDocument(document) },
                            onReturnClick = {
                                navController.navigate(
                                    Screens.AddRepairReturnDocument.createRoute(document.id)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun RepairDocumentCard(
    repairDocument: RepairDocument,
    fixedAssetName: String,
    inventoryNumber: String,
    serviceCenterName: String,
    repairTypeName: String,
    onDeleteClick: () -> Unit,
    onReturnClick: () -> Unit
) {
    val formattedDate = remember(repairDocument.creationDate) {
        SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            .format(Date(repairDocument.creationDate))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Документ ремонта №${repairDocument.documentNumber}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Дата: $formattedDate",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Основное средство: $fixedAssetName ($inventoryNumber)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Сервисный центр: $serviceCenterName",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Тип ремонта: $repairTypeName",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Стоимость: ${repairDocument.repairCost} руб.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Срок ремонта: ${repairDocument.repairDuration} дней",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column {
                    IconButton(
                        onClick = onReturnClick,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Вернуть с ремонта",
                            tint = Color.Green
                        )
                    }
                    
                }
            }
        }
    }
}