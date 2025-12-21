package com.example.cursova.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.cursova.viewModel.HallViewModel
import com.example.cursova.viewModel.ResponsibleViewModel

@Composable
fun AddResponsible(
    navController: NavController,
    viewModel: ResponsibleViewModel = hiltViewModel(),
    hallViewModel: HallViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var selectedHallId by remember { mutableStateOf(-1) }
    var errorMessage by remember { mutableStateOf("") }
    var isHallDropdownExpanded by remember { mutableStateOf(false) }

    val halls by hallViewModel.halls.collectAsStateWithLifecycle()

    val gradient4 = Brush.linearGradient(
        colors = listOf(Color(0xFF5CE5D4), Color(0xFF06BDAA))
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
                            painter = painterResource(id = R.drawable.users),
                            contentDescription = "Ответственный за инвентарь",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(gradient4, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление ответственного за инвентарь",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = "Создание нового ответственного за инвентарь",
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

                    ){Text(
                        text = "Ответственный за инвентарь",
                        style = MaterialTheme.typography.titleMedium
                    )
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            shape = RoundedCornerShape(28.dp),
                            label = { Text("Ответственный") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        if (errorMessage.isNotEmpty()) {
                            Text(
                                text = errorMessage,
                                color = Color.Red,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        Text(
                            text = "Зал",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { isHallDropdownExpanded = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(55.dp)
                            ) {
                                Box(

                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = if (selectedHallId != -1) halls.find { it.id == selectedHallId }?.name ?: "Выберите зал" else "Выберите зал",
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = "Открыть список залов",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .align(Alignment.TopEnd)
                                            .padding(start = 35.dp)
                                    )
                                }
                            }
                            DropdownMenu(
                                expanded = isHallDropdownExpanded,
                                onDismissRequest = { isHallDropdownExpanded = false },
                                modifier = Modifier
                                    .width(IntrinsicSize.Max) // Устанавливаем ширину выпадающего списка равной ширине кнопки
                                    
                            ) {
                                halls.forEach { hall ->
                                    DropdownMenuItem(
                                        text = { Text(hall.name) },
                                        onClick = {
                                            selectedHallId = hall.id
                                            isHallDropdownExpanded = false
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
            Button(

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Цвет фона кнопки
                    // Цвет текста на кнопке
                ),
                onClick = {
                    if (name.isNotBlank() && selectedHallId != -1) {
                        val responsible = com.example.cursova.Responsible.Responsible(
                            name = name,
                            hallId = selectedHallId
                        )
                        viewModel.addResponsible(responsible)
                        navController.popBackStack()
                    } else {
                        errorMessage = "Наименование и зал должны быть заполнены"
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
                    containerColor = Color.Transparent, // Цвет фона кнопки
                    // Цвет текста на кнопке
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


