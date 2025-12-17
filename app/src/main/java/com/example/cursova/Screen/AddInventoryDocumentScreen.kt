package com.example.cursova.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.cursova.Inventory.InventoryDocument
import com.example.cursova.Inventory.InventoryItem
import com.example.cursova.R
import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.InventoryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInventoryDocumentScreen(
    navController: NavController,
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel()
) {
    val fixedAssets by fixedAssetViewModel.fixedAssets.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf("") }

    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xFF9B65FF), Color(0xFF5D00FF)),

        )
    val gradient5 = Brush.linearGradient(
        colors = listOf(Color(0xD500FF07), Color(0xDF009306))
    )
    val gradient6 = Brush.linearGradient(
        colors = listOf(Color(0xFFFA5555), Color(0xFFFF0000))
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
                            painter = painterResource(id = R.drawable.clipboardlist), // Замените на ваш ресурс
                            contentDescription = "Документ инвентаризации",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(gradient2, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Создание документа инвентаризации",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Отметьте оборудование, находящееся в зале",
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
                        Text(
                            text = "Список оборудования:",
                            style = MaterialTheme.typography.titleMedium
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(fixedAssets) { fixedAsset ->
                                var isPresent by rememberSaveable { mutableStateOf(false) }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isPresent,
                                        onCheckedChange = { newValue ->
                                            isPresent = newValue
                                        }
                                    )
                                    Column {
                                        Text(
                                            text = fixedAsset.name,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                        Text(
                                            text = "Инвентарный номер: ${fixedAsset.inventoryNumber}",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                }



            }
        }



        Spacer(modifier = Modifier.weight(1f))

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
                        .padding(bottom = 16.dp)
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = {
                    // Логика сохранения документа инвентаризации
                    coroutineScope.launch {
                        try {
                            val documentNumber = inventoryViewModel.generateDocumentNumber()
                            val creationDate = System.currentTimeMillis()

                            val inventoryDocument = InventoryDocument(
                                documentNumber = documentNumber,
                                creationDate = creationDate
                            )

                            inventoryViewModel.addInventoryDocument(inventoryDocument)

                            val newDocumentId = inventoryViewModel.getMaxDocumentId() ?: -1

                            fixedAssets.forEach { fixedAsset ->
                                // Здесь можно добавить логику для сохранения информации о наличии оборудования
                            }

                            navController.popBackStack()
                        } catch (e: Exception) {
                            errorMessage = "Ошибка при создании документа: ${e.message}"
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






