package com.example.cursova.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.data.Nomenclature
import com.example.cursova.viewModel.NomenclatureViewModel





@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nomenclature(
    navController: NavController,
    viewModel: NomenclatureViewModel = hiltViewModel()
) {
    val nomens by viewModel.nomen.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Номенклатура") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Implement search functionality */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* TODO: Implement add functionality */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(nomens) { nomen ->
                NomenCard(
                    nomen = nomen,
                    onDeleteClick = { viewModel.deleteNomen(nomen) }
                )
            }
        }
    }
}



@Composable
fun NomenCard(
    nomen: Nomenclature,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = nomen.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = nomen.type,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = nomen.price.toString() + " ₽",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "за шт",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Удалить номенклатуру"
                )
            }
        }
    }
}






//@Composable
//fun Nomenclature(
//    navController: NavController,
//    viewModel: NomenclatureViewModel = hiltViewModel()
//) {
//    val nomens by viewModel.nomen.collectAsStateWithLifecycle()
//
//
//
//    LazyColumn (
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ){
//        items(nomens){nomen ->
//            NomenCard(
//                nomen = nomen,
//                onDeleteClick = { viewModel.deleteNomen(nomen) } // Передача реального обработчика
//            )
//        }
//    }
//}
//
//@Composable
//fun NomenCard(
//    nomen: Nomenclature,
//    onDeleteClick: () -> Unit
//){
//
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(
//                text = nomen.name
//            )
//            Text(
//                text = nomen.type
//            )
//            IconButton(onClick = onDeleteClick) {
//                Icon(
//                    Icons.Default.Delete,
//                    contentDescription = "Удалить номенклатуру"
//                )
//            }
//        }
//    }
//
//}