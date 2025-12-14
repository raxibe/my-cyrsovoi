package com.example.cursova.Screen

import android.util.Log
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

import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.WriteOffDocument.WriteOffDocument
import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.WriteOffDocumentViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWriteOffDocument(
    navController: NavController,
    viewModel: WriteOffDocumentViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel()
) {
    var fixedAssetId by remember { mutableStateOf(-1) }
    var isFixedAssetDropdownExpanded by remember { mutableStateOf(false) }
    var reason by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val fixedAssets by fixedAssetViewModel.fixedAssets.collectAsStateWithLifecycle()
    val unsuitableFixedAssets = remember(fixedAssets) {
        val filteredAssets = fixedAssets.filter { it.status == "непригодно к использованию" }
        Log.d("UnsuitableAssets", "Filtered assets: $filteredAssets") // Логирование
        filteredAssets
    }

    val coroutineScope = rememberCoroutineScope()
    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xFFB64EC5), Color(0xFFA21AB9))
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
                            painter = painterResource(id = R.drawable.usercheck),
                            contentDescription = "Списание",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(gradient2, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление документа списания",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Создание нового документа списания",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))

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
                                            .padding(top = 11.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список основных средств",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .padding(start = 35.dp)
                                            .align(Alignment.TopEnd)
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

                        Text(
                            text = "Причина списания",
                            style = MaterialTheme.typography.titleMedium
                        )
                        OutlinedTextField(
                            value = reason,
                            onValueChange = { reason = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            label = { Text("Причина") }
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
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = {
                    if (fixedAssetId == -1) {
                        errorMessage = "Выберите основное средство"
                    } else if (reason.isEmpty()) {
                        errorMessage = "Укажите причину списания"
                    } else {
                        coroutineScope.launch {
                            try {
                                val documentNumber = viewModel.generateDocumentNumber()
                                val creationDate = System.currentTimeMillis()

                                val writeOffDocument = WriteOffDocument(
                                    documentNumber = documentNumber,
                                    creationDate = creationDate,
                                    fixedAssetId = fixedAssetId,
                                    reason = reason
                                )

                                viewModel.addWriteOffDocument(writeOffDocument)
                                navController.popBackStack()
                            } catch (e: Exception) {
                                errorMessage = "Ошибка при создании документа: ${e.message}"
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



