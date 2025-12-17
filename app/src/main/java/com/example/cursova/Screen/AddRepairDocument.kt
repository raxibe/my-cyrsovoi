package com.example.cursova.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRepairDocument(
    navController: NavController,
    repairDocumentViewModel: RepairDocumentViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel(),
    serviceCenterViewModel: ServiceCenterViewModel = hiltViewModel(),
    repairViewModel: RepairViewModel = hiltViewModel()
) {
    var fixedAssetId by remember { mutableStateOf(-1) }
    var serviceCenterId by remember { mutableStateOf(-1) }
    var repairTypeId by remember { mutableStateOf(-1) }
    var repairCost by remember { mutableStateOf("") }
    var repairDuration by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var isFixedAssetDropdownExpanded by remember { mutableStateOf(false) }
    var isServiceCenterDropdownExpanded by remember { mutableStateOf(false) }
    var isRepairTypeDropdownExpanded by remember { mutableStateOf(false) }

    val fixedAssets by fixedAssetViewModel.fixedAssets.collectAsStateWithLifecycle()
    val serviceCenters by serviceCenterViewModel.serviceCenters.collectAsStateWithLifecycle()
    val repairTypes by repairViewModel.repair.collectAsStateWithLifecycle()

    val unsuitableFixedAssets = remember(fixedAssets) {
        fixedAssets.filter { it.status == "непригодно к использованию" }
    }

    val coroutineScope = rememberCoroutineScope()
    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xEEFF9800), Color(0xFFFF3A00)),

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.фонпервогоэкрана))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colorResource(id = R.color.фонпервогоэкрана),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(colorResource(id = R.color.фонпервогоэкрана))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.upload),
                            contentDescription = "Ремонт",
                            modifier = Modifier
                                .size(48.dp)
                                .background(gradient2, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление документа сдачи в ремонт",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Создание нового документа сдачи в ремонт",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()) // Добавлена прокрутка
                    ) {
                        // Выбор основного средства
                        Text(
                            text = "Основное средство",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isFixedAssetDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(65.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (fixedAssetId != -1) {
                                            val asset = unsuitableFixedAssets.find { it.id == fixedAssetId }
                                            "${asset?.name ?: ""} (${asset?.inventoryNumber ?: ""})"
                                        } else {
                                            "Выберите основное средство"
                                        },
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список залов",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .align(Alignment.TopEnd)
                                            .padding(start = 35.dp)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isFixedAssetDropdownExpanded,
                                onDismissRequest = { isFixedAssetDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                if (unsuitableFixedAssets.isEmpty()) {
                                    DropdownMenuItem(
                                        text = { Text("Нет непригодных основных средств") },
                                        onClick = { isFixedAssetDropdownExpanded = false }
                                    )
                                } else {
                                    unsuitableFixedAssets.forEach { asset ->
                                        DropdownMenuItem(
                                            text = { Text("${asset.name} (${asset.inventoryNumber})") },
                                            onClick = {
                                                fixedAssetId = asset.id
                                                isFixedAssetDropdownExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Выбор сервисного центра
                        Text(
                            text = "Сервисный центр",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isServiceCenterDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(65.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (serviceCenterId != -1) {
                                            val center = serviceCenters.find { it.id == serviceCenterId }
                                            center?.name ?: "Выберите сервисный центр"
                                        } else {
                                            "Выберите сервисный \nцентр"
                                        },
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список залов",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .align(Alignment.TopEnd)
                                            .padding(start = 35.dp)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isServiceCenterDropdownExpanded,
                                onDismissRequest = { isServiceCenterDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                serviceCenters.forEach { center ->
                                    DropdownMenuItem(
                                        text = { Text(center.name) },
                                        onClick = {
                                            serviceCenterId = center.id
                                            isServiceCenterDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Выбор типа ремонта
                        Text(
                            text = "Тип ремонта",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isRepairTypeDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(65.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (repairTypeId != -1) {
                                            val type = repairTypes.find { it.id == repairTypeId }
                                            type?.name ?: "Выберите тип ремонта"
                                        } else {
                                            "Выберите тип ремонта"
                                        },
                                        modifier = Modifier
                                            .padding(top = 14.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список залов",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .align(Alignment.TopEnd)
                                            .padding(start = 35.dp)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isRepairTypeDropdownExpanded,
                                onDismissRequest = { isRepairTypeDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                repairTypes.forEach { type ->
                                    DropdownMenuItem(
                                        text = { Text(type.name) },
                                        onClick = {
                                            repairTypeId = type.id
                                            isRepairTypeDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Поле для стоимости ремонта
                        Text(
                            text = "Стоимость ремонта",
                            style = MaterialTheme.typography.titleMedium
                        )
                        OutlinedTextField(
                            value = repairCost,
                            onValueChange = { repairCost = it },
                            shape = RoundedCornerShape(28.dp),
                            modifier = Modifier
                                .fillMaxWidth()

                                .padding(top = 8.dp),
                            label = { Text("Стоимость") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Поле для сроков ремонта
                        Text(
                            text = "Сроки ремонта (в днях)",
                            style = MaterialTheme.typography.titleMedium
                        )
                        OutlinedTextField(
                            value = repairDuration,
                            onValueChange = { repairDuration = it },
                            shape = RoundedCornerShape(28.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            label = { Text("Количество дней") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val gradient5 = Brush.linearGradient(
            colors = listOf(Color(0xD500FF07), Color(0xDF009306))
        )
        val gradient6 = Brush.linearGradient(
            colors = listOf(Color(0xFFFA5555), Color(0xFFFF0000))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = {
                    if (fixedAssetId == -1) {
                        errorMessage = "Выберите основное средство"
                    } else if (serviceCenterId == -1) {
                        errorMessage = "Выберите сервисный центр"
                    } else if (repairTypeId == -1) {
                        errorMessage = "Выберите тип ремонта"
                    } else if (repairCost.isEmpty()) {
                        errorMessage = "Укажите стоимость ремонта"
                    } else if (repairDuration.isEmpty()) {
                        errorMessage = "Укажите сроки ремонта"
                    } else {
                        coroutineScope.launch {
                            try {
                                val documentNumber = repairDocumentViewModel.generateDocumentNumber()
                                val creationDate = System.currentTimeMillis()

                                val repairDocument = RepairDocument(
                                    documentNumber = documentNumber,
                                    creationDate = creationDate,
                                    fixedAssetId = fixedAssetId,
                                    serviceCenterId = serviceCenterId,
                                    repairTypeId = repairTypeId,
                                    repairCost = repairCost.toDouble(),
                                    repairDuration = repairDuration.toInt()
                                )

                                repairDocumentViewModel.addRepairDocument(repairDocument)
                                navController.popBackStack()
                            } catch (e: Exception) {
                                errorMessage = "Ошибка при создании документа: ${e.message}"
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .background(gradient5, shape = RoundedCornerShape(50.dp))
            ) {
                Text("Создать")
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(gradient6, shape = RoundedCornerShape(50.dp))
            ) {
                Text("Отмена")
            }
        }
    }
}



