package com.example.cursova.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cursova.viewModel.AcceptanceDocumentViewModel
import com.example.cursova.viewModel.ItemViewModel
import com.example.cursova.viewModel.ResponsibleViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cursova.AcceptanceDocument.AcceptanceDocument
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.R
import com.example.cursova.purchase.Item
import com.example.cursova.viewModel.FixedAssetViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAcceptanceDocument(
    navController: NavController,
    acceptanceDocumentViewModel: AcceptanceDocumentViewModel = hiltViewModel(),
    responsibleViewModel: ResponsibleViewModel = hiltViewModel(),
    itemViewModel: ItemViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel()

) {



    // Переменные для передающего лица
    var transferPersonId by remember { mutableStateOf(-1) }
    var isTransferPersonDropdownExpanded by remember { mutableStateOf(false) }

    // Переменные для МОЛ
    var responsibleId by remember { mutableStateOf(-1) }
    var isResponsibleDropdownExpanded by remember { mutableStateOf(false) }

    // Переменные для товара
    var selectedItemId by remember { mutableStateOf(-1) }
    var isItemDropdownExpanded by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    val responsibles by responsibleViewModel.responsibles.collectAsStateWithLifecycle()
    val items by itemViewModel.items.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()
    val gradient5 = Brush.linearGradient(
        colors = listOf(Color(0xD500FF07), Color(0xDF009306))
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
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "Документ принятия к учету",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(gradient5, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление документа принятия к учету",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Создание нового документа принятия к учету",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
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
                    ) {
                        // Передающее лицо
                        Text(
                            text = "Передающее лицо",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isTransferPersonDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(65.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (transferPersonId != -1) responsibles.find { it.id == transferPersonId }?.name ?: "Выберите передающее лицо" else "Выберите передающее лицо",
                                        modifier = Modifier
                                            .padding(top = 6.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список передающих лиц",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .padding(start = 35.dp)
                                            .align(Alignment.TopEnd)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isTransferPersonDropdownExpanded,
                                onDismissRequest = { isTransferPersonDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                responsibles.forEach { responsible ->
                                    DropdownMenuItem(
                                        text = { Text(responsible.name) },
                                        onClick = {
                                            transferPersonId = responsible.id
                                            isTransferPersonDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Материально-ответственное лицо
                        Text(
                            text = "Материально-ответственное лицо",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isResponsibleDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (responsibleId != -1) responsibles.find { it.id == responsibleId }?.name ?: "Выберите МОЛ" else "Выберите МОЛ",
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список МОЛ",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .padding(start = 35.dp)
                                            .align(Alignment.TopEnd)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isResponsibleDropdownExpanded,
                                onDismissRequest = { isResponsibleDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                responsibles.forEach { responsible ->
                                    DropdownMenuItem(
                                        text = { Text(responsible.name) },
                                        onClick = {
                                            responsibleId = responsible.id
                                            isResponsibleDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Товар
                        Text(
                            text = "Товар",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isItemDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (selectedItemId != -1) items.find { it.id == selectedItemId }?.name ?: "Выберите товар" else "Выберите товар",
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список товаров",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .padding(start = 35.dp)
                                            .align(Alignment.TopEnd)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isItemDropdownExpanded,
                                onDismissRequest = { isItemDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                items.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text("${item.name} (${item.quantity} шт.)") },
                                        onClick = {
                                            selectedItemId = item.id
                                            isItemDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
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
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = {
                    if (transferPersonId == -1) {
                        errorMessage = "Выберите передающее лицо"
                    } else if (responsibleId == -1) {
                        errorMessage = "Выберите материально-ответственное лицо"
                    } else if (selectedItemId == -1) {
                        errorMessage = "Выберите товар"
                    } else {
                        coroutineScope.launch {
                            val selectedItem = items.find { it.id == selectedItemId }
                            if (selectedItem == null) {
                                errorMessage = "Выбранный товар не найден"
                            } else if (selectedItem.quantity <= 0) {
                                errorMessage = "Недостаточно товара на складе"
                            } else {
                                try {
                                    val documentNumber = acceptanceDocumentViewModel.generateDocumentNumber()
                                    val creationDate = System.currentTimeMillis()

                                    val acceptanceDocument = AcceptanceDocument(
                                        documentNumber = documentNumber,
                                        creationDate = creationDate,
                                        transferPerson = responsibles.find { it.id == transferPersonId }?.name ?: "",
                                        responsibleId = responsibleId,
                                        items = selectedItem.name
                                    )

                                    // Генерация уникального инвентарного номера
                                    val inventoryNumber = fixedAssetViewModel.generateUniqueInventoryNumber()

                                    val fixedAsset = FixedAsset(
                                        name = selectedItem.name,
                                        inventoryNumber = inventoryNumber,
                                        responsibleId = responsibleId,
                                        acceptanceDocumentId = 0,
                                        status = "в зале"
                                    )

                                    acceptanceDocumentViewModel.addAcceptanceDocument(acceptanceDocument, fixedAsset)

                                    val updatedItem = selectedItem.copy(quantity = selectedItem.quantity - 1)
                                    itemViewModel.updateItem(updatedItem)

                                    navController.popBackStack()
                                } catch (e: Exception) {
                                    errorMessage = "Ошибка при создании документа: ${e.message}"
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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
                    .padding(bottom = 25.dp)
                    .background(gradient6, shape = RoundedCornerShape(50.dp))
            ) {
                Text("Отмена")
            }
        }
    }
}