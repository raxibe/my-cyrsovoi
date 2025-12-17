package com.example.cursova.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.purchase.PurchaseDocument
import com.example.cursova.viewModel.PurchaseDocumentViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchaseDocuments(
    navController: NavController,
    viewModel: PurchaseDocumentViewModel = hiltViewModel()
) {
    val purchaseDocuments by viewModel.purchaseDocuments.collectAsStateWithLifecycle()

    // Рассчитываем общую сумму всех товаров
    val totalSumAll = remember(purchaseDocuments) {
        purchaseDocuments.sumOf { document ->
            document.items.lines().sumOf { line ->
                val parts = line.split(", ")
                val quantity = parts.find { it.startsWith("Количество:") }?.split(": ")?.get(1)?.toIntOrNull() ?: 0
                val price = parts.find { it.startsWith("Цена:") }?.split(": ")?.get(1)?.toDoubleOrNull() ?: 0.0
                quantity * price
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Документы закупки") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.AddPurchaseDocuments.route) }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
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
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(purchaseDocuments) { document ->
                        // Рассчитываем сумму для каждого документа
                        val documentSum = document.items.lines().sumOf { line ->
                            val parts = line.split(", ")
                            val quantity = parts.find { it.startsWith("Количество:") }?.split(": ")?.get(1)?.toIntOrNull() ?: 0
                            val price = parts.find { it.startsWith("Цена:") }?.split(": ")?.get(1)?.toDoubleOrNull() ?: 0.0
                            quantity * price
                        }
                        PurchaseDocumentCard(
                            purchaseDocument = document,
                            onDeleteClick = { viewModel.deletePurchaseDocument(document) },
                            totalSum = documentSum
                        )
                    }
                }
                // Отображаем общую сумму всех товаров

            }
        }
    }
}

@Composable
fun PurchaseDocumentCard(
    purchaseDocument: PurchaseDocument,
    onDeleteClick: () -> Unit,
    totalSum: Double // Добавляем параметр для суммы документа
) {
    val formattedDate = remember(purchaseDocument.creationDate) {
        SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            .format(Date(purchaseDocument.creationDate))
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
                        text = "Документ закупки №${purchaseDocument.documentNumber}",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Дата: $formattedDate",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Поставщик: ${purchaseDocument.supplierName}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Товары:\n${purchaseDocument.items}",
                        style = MaterialTheme.typography.bodyMedium,
                        softWrap = true,
                        overflow = TextOverflow.Visible
                    )
                    Text(
                        text = "Сумма к оплате: $totalSum руб.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                }

            }
        }
    }
}