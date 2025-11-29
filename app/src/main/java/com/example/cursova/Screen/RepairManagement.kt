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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
fun RepairManagement(
    navController: NavController
) {
    Box(modifier = Modifier
        .background(colorResource(id = R.color.фонпервогоэкрана))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()

            Spacer(modifier = Modifier.height(50.dp))

            ModulesSection()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp, start = 6.dp)
    ) {

        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                modifier = Modifier,
                text = "Управление ремонтом",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "4 раздела",
            )
        }
    }
}

@Composable
fun ModulesSection() {

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF9B65FF), Color(0xFF5D00FF)),

        )

    val gradient2 = Brush.linearGradient(
        colors = listOf(Color(0xEEFF9800), Color(0xFFFF3A00)),

        )

    val gradient3 = Brush.linearGradient(
        colors = listOf(Color(0xD500FF07), Color(0xDF009306))
    )

    val gradient4 = Brush.linearGradient(
        colors = listOf(Color(0xDA008DFF), Color(0xE40029FF))
    )


    Column {
        ModuleCard(
            iconResId = R.drawable.list,
            title = "Список видов ремонта",
            description = "Просмотр и управление видами ремонта",
            iconColor = gradient, // Фиолетовый цвет иконки


        )
        Spacer(modifier = Modifier.height(16.dp))
        ModuleCard(
            iconResId = R.drawable.upload,
            title = "Сдача в ремонт",
            description = "Оформление документов на сдачу",
            iconColor = gradient2, // Оранжевый цвет иконки

        )
        Spacer(modifier = Modifier.height(16.dp))
        ModuleCard(
            iconResId = R.drawable.download,
            title = "Возврат с ремонта",
            description = "Приемка оборудования из ремонта",
            iconColor = gradient3, // Зеленый цвет иконки

        )
        Spacer(modifier = Modifier.height(16.dp))
        ModuleCard(
            iconResId = R.drawable.building,
            title = "Сервисные центры",
            description = "Список и контакты сервисных центров",
            iconColor = gradient4, // Синий цвет иконки

        )
    }
}

@Composable
fun ModuleCard(iconResId: Int, title: String, description: String, iconColor: Brush,) {
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