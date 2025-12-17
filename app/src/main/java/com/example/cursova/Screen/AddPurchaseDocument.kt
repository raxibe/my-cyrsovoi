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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cursova.Nomenclature.Nomenclature
import com.example.cursova.R
import com.example.cursova.purchase.Item

import com.example.cursova.purchase.PurchaseDocument
import com.example.cursova.viewModel.ItemViewModel
import com.example.cursova.viewModel.NomenclatureViewModel
import com.example.cursova.viewModel.PurchaseDocumentViewModel
import com.example.cursova.viewModel.SupplierViewModel
import kotlinx.coroutines.launch

@Composable
fun AddPurchaseDocument(
    navController: NavController,
    viewModel: PurchaseDocumentViewModel = hiltViewModel(),
    supplierViewModel: SupplierViewModel = hiltViewModel(),
    nomenclatureViewModel: NomenclatureViewModel = hiltViewModel(),
    itemViewModel: ItemViewModel = hiltViewModel()
) {
    var supplierId by remember { mutableStateOf(-1) }
    var isSupplierDropdownExpanded by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf(listOf<Item>()) }
    var errorMessage by remember { mutableStateOf("") }
    val suppliers by supplierViewModel.suppliers.collectAsStateWithLifecycle()
    val nomens by nomenclatureViewModel.nomen.collectAsStateWithLifecycle()
    val allItems by itemViewModel.items.collectAsStateWithLifecycle()
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xDA008DFF), Color(0xE40029FF))
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
                            painter = painterResource(id = R.drawable.filetext),
                            contentDescription = "Документ закупки",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(gradient, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление документа закупки",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Создание нового документа закупки",
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
                            text = "Документ закупки",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Поставщик",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isSupplierDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (supplierId != -1) suppliers.find { it.id == supplierId }?.name
                                            ?: "Выберите поставщика" else "Выберите поставщика",
                                        modifier = Modifier
                                            .padding(top = 11.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список поставщиков",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .padding(start = 35.dp)
                                            .align(Alignment.TopEnd)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isSupplierDropdownExpanded,
                                onDismissRequest = { isSupplierDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                            ) {
                                suppliers.forEach { supplier ->
                                    DropdownMenuItem(
                                        text = { Text(supplier.name) },
                                        onClick = {
                                            supplierId = supplier.id
                                            isSupplierDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Товары",
                            style = MaterialTheme.typography.titleMedium
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            items(items) { item ->
                                ItemRow(
                                    item = item,
                                    nomens = nomens,
                                    onNameChange = { newName ->
                                        items =
                                            items.map { if (it == item) it.copy(name = newName) else it }
                                    },
                                    onQuantityChange = { newQuantity ->
                                        items =
                                            items.map { if (it == item) it.copy(quantity = newQuantity) else it }
                                    },
                                    onPriceChange = { newPrice ->
                                        items =
                                            items.map { if (it == item) it.copy(price = newPrice) else it }
                                    },
                                    allItems = allItems,
                                    itemViewModel = itemViewModel
                                )
                            }
                            item {
                                Button(
                                    onClick = {
                                        // Проверяем, есть ли уже товар с пустым именем или дубликат
                                        val hasEmptyName = items.any { it.name.isEmpty() }
                                        if (hasEmptyName) {
                                            errorMessage = "Заполните название товара"
                                        } else {
                                            val newItemName = "" // Пустое имя для нового товара
                                            val isDuplicate = items.any { it.name == newItemName && newItemName.isNotEmpty() }
                                            if (isDuplicate) {
                                                errorMessage = "Этот товар уже добавлен"
                                            } else {
                                                items = items + Item(name = "", quantity = 1, price = 0.0)
                                            }
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                ) {
                                    Text("Добавить товар")
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

            val coroutineScope = rememberCoroutineScope()
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                onClick = {
                    val hasEmptyName = items.any { it.name.isEmpty() }
                    if (hasEmptyName) {
                        errorMessage = "Заполните названия всех товаров"
                    } else {
                        val hasDuplicateItems = items.groupingBy { it.name }.eachCount().any { it.value > 1 }
                        if (hasDuplicateItems) {
                            errorMessage = "Один и тот же товар добавлен несколько раз"
                        } else if (supplierId == -1) {
                            errorMessage = "Необходимо выбрать поставщика"
                        } else if (items.isEmpty()) {
                            errorMessage = "Необходимо добавить хотя бы один товар"
                        } else if (items.any { it.quantity <= 0 || it.price <= 0.0 }) {
                            errorMessage = "Количество и цена должны быть больше нуля"
                        } else {
                            val supplierName = suppliers.find { it.id == supplierId }?.name ?: ""

                            coroutineScope.launch {
                                val documentNumber = viewModel.generateDocumentNumber()
                                val creationDate = System.currentTimeMillis()

                                items.forEach { item ->
                                    val existingItem = allItems.find { it.name == item.name }
                                    if (existingItem != null) {
                                        val updatedItem = existingItem.copy(
                                            quantity = existingItem.quantity + item.quantity,
                                            price = item.price
                                        )
                                        itemViewModel.updateItem(updatedItem)
                                    } else {
                                        itemViewModel.addItem(item)
                                    }
                                }

                                val purchaseDocument = PurchaseDocument(
                                    supplierName = supplierName,
                                    items = items.joinToString("\n") { "${it.name}, Количество: ${it.quantity}, Цена: ${it.price}" },
                                    documentNumber = documentNumber,
                                    creationDate = creationDate
                                )
                                viewModel.addPurchaseDocument(purchaseDocument)
                                navController.popBackStack()
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


@Composable
fun ItemRow(
    item: Item,
    nomens: List<Nomenclature>,
    onNameChange: (String) -> Unit,
    onQuantityChange: (Int) -> Unit,
    onPriceChange: (Double) -> Unit,
    allItems: List<Item>, // Добавляем список всех товаров
    itemViewModel: ItemViewModel = hiltViewModel() // Добавляем ItemViewModel
) {
    var isNomenDropdownExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
        ) {
            OutlinedButton(
                onClick = { isNomenDropdownExpanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = if (item.name.isNotEmpty()) item.name else "Выберите товар",
                        modifier = Modifier
                            .padding(top = 11.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Открыть список товаров",
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.TopEnd)
                            .padding(start = 35.dp)
                    )
                }
            }
            DropdownMenu(
                expanded = isNomenDropdownExpanded,
                onDismissRequest = { isNomenDropdownExpanded = false },
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                nomens.forEach { nomen ->
                    DropdownMenuItem(
                        text = { Text(nomen.name) },
                        onClick = {
                            onNameChange(nomen.name)
                            // Подгружаем данные о товаре из базы, если он уже существует
                            val existingItem = allItems.find { it.name == nomen.name }
                            if (existingItem != null) {
                                onQuantityChange(1) // или existingItem.quantity, если нужно
                                onPriceChange(existingItem.price)
                            }
                            isNomenDropdownExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = item.quantity.toString(),
            onValueChange = { newQuantity ->
                onQuantityChange(newQuantity.toIntOrNull() ?: 0)
            },
            label = { Text("Количество") },
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = item.price.toString(),
            shape = RoundedCornerShape(28.dp),
            onValueChange = { newPrice ->
                onPriceChange(newPrice.toDoubleOrNull() ?: 0.0)
            },
            label = { Text("Цена") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Сумма: ${item.quantity * item.price}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}






