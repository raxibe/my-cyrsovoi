package com.example.cursova.Screen

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.Inventory.InventoryDocument
import com.example.cursova.R

import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.InventoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryDocumentDetailsScreen(
    navController: NavController,
    documentId: Int,
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel()
) {
    var inventoryDocument by remember { mutableStateOf<InventoryDocument?>(null) }
    val inventoryItems by inventoryViewModel.getInventoryItemsByDocumentId(documentId).collectAsState(initial = emptyList())
    val fixedAssets by fixedAssetViewModel.fixedAssets.collectAsStateWithLifecycle()

    LaunchedEffect(documentId) {
        inventoryDocument = withContext(Dispatchers.IO) {
            inventoryViewModel.getInventoryDocumentById(documentId)
        }
    }

    LaunchedEffect(inventoryItems) {
        Log.d("InventoryItems", "Items loaded: ${inventoryItems.size}")
        inventoryItems.forEach { item ->
            Log.d("InventoryItems", "Item: ${item.fixedAssetId}, Present: ${item.isPresent}")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали документа") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.фонпервогоэкрана)
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.фонпервогоэкрана))
                .padding(paddingValues)
        ) {
            if (inventoryDocument == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    val formattedDate = remember(inventoryDocument?.creationDate) {
                        inventoryDocument?.let {
                            SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                                .format(Date(it.creationDate))
                        } ?: ""
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
                                .padding(20.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Документ инвентаризации №${inventoryDocument?.documentNumber ?: ""}",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Дата: $formattedDate",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Список оборудования:",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    if (inventoryItems.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Нет оборудования в документе")
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(inventoryItems) { item ->
                                val fixedAsset = fixedAssets.find { it.id == item.fixedAssetId }
                                InventoryItemCard(
                                    fixedAssetName = fixedAsset?.name ?: "Неизвестное оборудование",
                                    inventoryNumber = fixedAsset?.inventoryNumber ?: "Неизвестно",
                                    isPresent = item.isPresent
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InventoryItemCard(
    fixedAssetName: String,
    inventoryNumber: String,
    isPresent: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = fixedAssetName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Инвентарный номер: $inventoryNumber",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Icon(
                    imageVector = if (isPresent) Icons.Default.Check else Icons.Default.Close,
                    contentDescription = if (isPresent) "Присутствует" else "Отсутствует",
                    tint = if (isPresent) Color.Green else Color.Red
                )
            }
        }
    }
}
