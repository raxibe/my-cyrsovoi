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
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.WriteOffDocument.WriteOffDocument
import com.example.cursova.viewModel.FixedAssetViewModel
import com.example.cursova.viewModel.WriteOffDocumentViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteOffDocuments(
    navController: NavController,
    viewModel: WriteOffDocumentViewModel = hiltViewModel(),
    fixedAssetViewModel: FixedAssetViewModel = hiltViewModel()
) {
    val writeOffDocuments by viewModel.writeOffDocuments.collectAsStateWithLifecycle()
    val fixedAssets by fixedAssetViewModel.fixedAssets.collectAsStateWithLifecycle()

    // Сортируем документы по дате создания в обратном порядке
    val sortedWriteOffDocuments = remember(writeOffDocuments) {
        writeOffDocuments.sortedByDescending { it.creationDate }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Документы списания") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.AddWriteOffDocument.route) }) {
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
            if (sortedWriteOffDocuments.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Нет документов списания")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(sortedWriteOffDocuments) { document ->
                        val fixedAsset = fixedAssets.find { it.id == document.fixedAssetId }
                        WriteOffDocumentCard(
                            writeOffDocument = document,
                            fixedAssetName = fixedAsset?.name ?: "",
                            inventoryNumber = fixedAsset?.inventoryNumber ?: "",
                            onDeleteClick = { viewModel.deleteWriteOffDocument(document) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WriteOffDocumentCard(
    writeOffDocument: WriteOffDocument,
    fixedAssetName: String,
    inventoryNumber: String,
    onDeleteClick: () -> Unit
) {
    val formattedDate = remember(writeOffDocument.creationDate) {
        SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            .format(Date(writeOffDocument.creationDate))
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
                        text = "Документ списания №${writeOffDocument.documentNumber}",
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
                        text = "Причина: ${writeOffDocument.reason}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
    }
}
