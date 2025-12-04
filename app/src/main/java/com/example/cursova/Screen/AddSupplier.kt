package com.example.cursova.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavController
import com.example.cursova.R
import com.example.cursova.viewModel.SupplierViewModel

@Composable
fun AddSupplier(
    navController: NavController,
    viewModel: SupplierViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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
                            contentDescription = "Поставщик",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.CenterHorizontally)
                                .background(gradient2, shape = RoundedCornerShape(7.dp))
                                .padding(7.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Добавление поставщика",
                            style = MaterialTheme.typography.headlineMedium,

                        )
                        Text(
                            text = "Создание нового поставщика",
                            style = MaterialTheme.typography.bodyMedium,

                        )
                    }
                }

                Spacer(modifier = Modifier.height(160.dp))


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
                        text = "Поставщик",
                        style = MaterialTheme.typography.titleMedium
                    )
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Наименование*") },
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
                        }}

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
                    containerColor = Color.Transparent, // Цвет фона кнопки
                    // Цвет текста на кнопке
                ),
                onClick = {
                    if (name.isNotBlank()) {
                        val supplier = com.example.cursova.Supplier.Supplier(name = name)
                        viewModel.addSupplier(supplier)
                        navController.popBackStack()
                    } else {
                        errorMessage = "Наименование должно быть заполнено"
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


