package com.example.cursova.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cursova.R


@Composable
fun ProcurementManagement(navController: NavController) {
    Box(modifier = Modifier
        .background(colorResource(id = R.color.фонпервогоэкрана))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, start = 12.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(
                        modifier = Modifier,
                        text = "Управление закупками",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "2 раздела",
                    )
                }
            }

            Spacer(modifier = Modifier.height(150.dp))

            ModulesSection2(navController)
        }
    }
}

@Composable
fun HeaderSection2() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp, start = 12.dp)
    ) {



        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                modifier = Modifier,
                text = "Управление закупками",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "2 раздела",
            )
        }
    }
}

@Composable
fun ModulesSection2(navController: NavController) {


    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xDA008DFF), Color(0xE40029FF))
    )

    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xFF5FBBEE), Color(0xFF03A9F4))
    )


    Column {
        ModuleCard2(
            iconResId = R.drawable.filetext,
            title = "Документ закупки",
            description = "Создание и управление документами закупок",
            iconColor = gradient, // Фиолетовый цвет иконки
            navController = navController

        )
        Spacer(modifier = Modifier.height(16.dp))
        ModuleCard2(
            iconResId = R.drawable.packag,
            title = "Список номенклатуры",
            description = "Справочник номенклатуры товаров",
            iconColor = gradient2, // Зеленый цвет иконки
            navController = navController


        )
    }
}

@Composable
fun ModuleCard2(
    iconResId: Int,
    title: String,
    description: String,
    iconColor: Brush,
    navController: NavController


) {
    Card(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(15.dp))
            .fillMaxWidth(0.9F)

            .height(140.dp),


        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Белый фон для содержимого внутри Card
                .clickable { // Здесь делаем карту кликабельной
                    when (title) {
                        "Документ закупки" -> navController.navigate(Screens.RepairManagment.route)
                        "Список номенклатуры" -> navController.navigate(Screens.Nomenclature.route)

                        // Добавляйте дополнительные условия для других карт
                    }
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(20.dp) // Паддинг внутри Card
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(iconColor, shape = RoundedCornerShape(7.dp)) // Фон для картинки
                ) {
                    Image(
                        painter = painterResource(id = iconResId),
                        contentDescription = "Module Icon",
                        modifier = Modifier.fillMaxSize()
                            .padding(7.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                    )
                }
            }
        }
    }
}