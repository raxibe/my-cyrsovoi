package com.example.cursova.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.RepairReturnDocument.RepairReturnDocument
import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.RepairDocumentViewModel
import com.example.cursova.viewModel.RepairReturnDocumentViewModel
import com.example.cursova.viewModel.RepairViewModel
import com.example.cursova.viewModel.ServiceCenterViewModel
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddRepairReturnDocument(
    navController: NavController,
    repairDocumentId: Int,
    repairReturnDocumentViewModel: RepairReturnDocumentViewModel = hiltViewModel(),
    repairDocumentViewModel: RepairDocumentViewModel = hiltViewModel()
) {
    var condition by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val repairDocuments by repairDocumentViewModel.repairDocuments.collectAsStateWithLifecycle()
    var repairDocument = remember(repairDocumentId, repairDocuments) {
        repairDocuments.find { it.id == repairDocumentId }
    }

    val coroutineScope = rememberCoroutineScope()
    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xFFB64EC5), Color(0xFFA21AB9))
    )

    LaunchedEffect(repairDocumentId) {
        repairDocument = repairDocumentViewModel.getRepairDocumentById(repairDocumentId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.фонпервогоэкрана))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .background(
                    colorResource(id = R.color.фонпервогоэкрана),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
                    .background(colorResource(id = R.color.фонпервогоэкрана))
            ) {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(300.dp)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.usercheck),
                            contentDescription = "Возврат с ремонта",
                            modifier = Modifier
                                .size(48.dp)
                                .background(gradient2, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление документа возврата с ремонта",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Создание нового документа возврата с ремонта",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .width(300.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Информация о документе ремонта
                Text(
                    text = "Документ ремонта №${repairDocument?.documentNumber ?: ""}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Основное средство: Оборудование №${repairDocument?.fixedAssetId ?: ""}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Сервисный центр: Центр №${repairDocument?.serviceCenterId ?: ""}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Тип ремонта: Тип №${repairDocument?.repairTypeId ?: ""}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Стоимость ремонта: ${repairDocument?.repairCost ?: ""} руб.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Срок ремонта: ${repairDocument?.repairDuration ?: ""} дней",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Поле для состояния оборудования
                Text(
                    text = "Состояние оборудования",
                    style = MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(
                    value = condition,
                    onValueChange = { condition = it },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 8.dp),
                    label = { Text("Опишите состояние оборудования") }
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val gradient5 = Brush.linearGradient(
            colors = listOf(Color(0xD500FF07), Color(0xDF009306))
        )
        val gradient6 = Brush.linearGradient(
            colors = listOf(Color(0xFFFA5555), Color(0xFFFF0000))
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(300.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = {
                    if (condition.isEmpty()) {
                        errorMessage = "Укажите состояние оборудования"
                    } else {
                        coroutineScope.launch {
                            try {
                                val documentNumber = repairReturnDocumentViewModel.generateDocumentNumber()
                                val creationDate = System.currentTimeMillis()

                                val repairReturnDocument = RepairReturnDocument(
                                    documentNumber = documentNumber,
                                    creationDate = creationDate,
                                    repairDocumentId = repairDocumentId,
                                    condition = condition
                                )

                                repairReturnDocumentViewModel.addRepairReturnDocument(repairReturnDocument)
                                navController.popBackStack()
                            } catch (e: Exception) {
                                errorMessage = "Ошибка при создании документа: ${e.message}"
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(300.dp)
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
                    .width(300.dp)
                    .padding(bottom = 16.dp)
                    .background(gradient6, shape = RoundedCornerShape(50.dp))
            ) {
                Text("Отмена")
            }
        }
    }
}


