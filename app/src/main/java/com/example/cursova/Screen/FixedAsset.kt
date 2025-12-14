package com.example.cursova.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.room.util.copy
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.R
import com.example.cursova.purchase.Item
import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixedAssets(
    navController: NavController,
    viewModel: FixedAssetViewModel = hiltViewModel()
) {
    val fixedAssets by viewModel.fixedAssets.collectAsStateWithLifecycle()
    var showStatusDialog by remember { mutableStateOf(false) }
    var selectedAssetId by remember { mutableStateOf(-1) }
    var selectedAssetStatus by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Основные средства") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
//
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            if (fixedAssets.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Нет основных средств")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(fixedAssets) { fixedAsset ->
                        FixedAssetCard(
                            fixedAsset = fixedAsset,
                            onClick = {
                                // Логика нажатия на карточку, например, переход на экран деталей
                            },
                            onStatusClick = { id, status ->
                                selectedAssetId = id
                                selectedAssetStatus = status
                                showStatusDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    StatusChangeDialog(
        showDialog = showStatusDialog,
        onDismiss = { showStatusDialog = false },
        onStatusChange = { newStatus ->
            if (selectedAssetId != -1) {
                viewModel.updateFixedAssetStatus(selectedAssetId, newStatus)
            }
        },
        currentStatus = selectedAssetStatus
    )
}

@Composable
fun FixedAssetCard(
    fixedAsset: FixedAsset,
    onClick: () -> Unit,
    onStatusClick: (Int, String) -> Unit
) {
    val statusColor = when (fixedAsset.status) {
        "в зале" -> Color.Green
        "в ремонте" -> Color.Blue
        "непригодно к использованию" -> Color.Red
        "списано" -> Color.Gray
        else -> Color.Black
    }

    Card(
        onClick = onClick,
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
                    .padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = fixedAsset.name,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Инвентарный номер: ${fixedAsset.inventoryNumber}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Box(
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                        .clickable { onStatusClick(fixedAsset.id, fixedAsset.status) }
                ) {
                    Text(
                        text = fixedAsset.status,
                        color = statusColor,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}


@Composable
fun StatusChangeDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onStatusChange: (String) -> Unit,
    currentStatus: String
) {
    val statusOptions = listOf("в зале", "в ремонте", "непригодно к использованию", "списано")

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Изменить статус") },
            text = {
                Column {
                    statusOptions.forEach { status ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onStatusChange(status)
                                    onDismiss()
                                }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (status == currentStatus),
                                onClick = {
                                    onStatusChange(status)
                                    onDismiss()
                                }
                            )
                            Text(text = status)
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Отмена")
                }
            }
        )
    }
}