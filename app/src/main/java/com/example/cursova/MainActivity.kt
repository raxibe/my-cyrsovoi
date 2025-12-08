package com.example.cursova

import FirstScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cursova.Screen.AddHall
import com.example.cursova.Screen.AddNomenclature
import com.example.cursova.Screen.AddPurchaseDocument
import com.example.cursova.Screen.AddRepair
import com.example.cursova.Screen.AddResponsible
import com.example.cursova.Screen.AddServiceCenter
import com.example.cursova.Screen.AddSupplier
import com.example.cursova.Screen.Hall
import com.example.cursova.Screen.Nomenclature
import com.example.cursova.Screen.ProcurementManagement
import com.example.cursova.Screen.PurchaseDocuments
import com.example.cursova.Screen.Repair
import com.example.cursova.Screen.RepairManagement
import com.example.cursova.Screen.Responsible
import com.example.cursova.Screen.Screens
import com.example.cursova.Screen.ServiceCenter
import com.example.cursova.Screen.Supplier
import com.example.cursova.Screen.WarehouseManagement
import com.example.cursova.ui.theme.CursovaTheme
import dagger.hilt.android.AndroidEntryPoint


// Основной класс активности остается прежним
// Основной класс активности остаётся таким же
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screens.FirstScreen.route,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                composable(Screens.FirstScreen.route) {
                    FirstScreen(navController = navController)
                }
                composable(Screens.RepairManagment.route) {
                    RepairManagement(navController = navController)
                }
                composable(Screens.WarehouseManagement.route) {
                    WarehouseManagement(navController = navController)
                }
                composable(Screens.ProcurementManagement.route) {
                    ProcurementManagement(navController = navController)
                }
                composable(Screens.Nomenclature.route) {
                    Nomenclature(navController = navController)
                }
                composable(Screens.AddNomenclature .route) {
                    AddNomenclature(navController = navController)
                }
                composable(Screens.Hall .route) {
                    Hall(navController = navController)
                }

                composable(Screens.AddHall.route) {
                    AddHall(navController = navController)
                }

                composable(Screens.Supplier .route) {
                    Supplier(navController = navController)
                }

                composable(Screens.AddSupplier.route) {
                    AddSupplier(navController = navController)
                }

                composable(Screens.Repair .route) {
                    Repair(navController = navController)
                }

                composable(Screens.AddRepair .route) {
                    AddRepair(navController = navController)
                }
                composable(Screens.ServiceCenter .route) {
                    ServiceCenter(navController = navController)
                }

                composable(Screens.AddServiceCenter .route) {
                    AddServiceCenter(navController = navController)
                }
                composable(Screens.Responsible .route) {
                    Responsible(navController = navController)
                }

                composable(Screens.AddResponsible .route) {
                    AddResponsible(navController = navController)
                }
                composable(Screens.PurchaseDocuments .route) {
                    PurchaseDocuments(navController = navController)
                }

                composable(Screens.AddPurchaseDocuments .route) {
                    AddPurchaseDocument(navController = navController)
                }




            }


        }
    }
}

//@Composable
//fun MainScreen() {
//
//    Box(
//        modifier = Modifier
//            .background(colorResource(id = R.color.фонпервогоэкрана))
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            HeaderSection()
//
//            Spacer(modifier = Modifier.height(150.dp))
//
//
//
//            ModulesSection()
//        }
//
//    }
//
//}
//
//@Composable
//fun HeaderSection() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 60.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.boxes),
//            contentDescription = "Module Icon",
//            modifier = Modifier.size(48.dp)
//        )
//        Spacer(modifier = Modifier.width(5.dp))
//        Column {
//            Text(
//                modifier = Modifier
//                    .padding(start = 25.dp),
//                text = "Учет склада",
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = "Спортивный комплекс",
//
//                )
//        }
//    }
//}
//
//
//@Composable
//fun ModulesSection() {
//
//
//    val gradient = Brush.linearGradient(
//        colors = listOf(Color(0xEEFF9800), Color(0xFFFF3A00)),
//
//        )
//    val gradient2 = Brush.linearGradient(
//        colors = listOf(Color(0xD500FF07), Color(0xDF009306))
//    )
//
//    val gradient3 = Brush.linearGradient(
//        colors = listOf(Color(0xDA008DFF), Color(0xE40029FF))
//    )
//
//
//    Column {
//        ModuleCard(
//            iconResId = R.drawable.wrench,
//            title = "Управление ремонтом",
//            description = "Виды ремонта, сдачи и возврат, сервисные центры",
//            sections = 4,
//            iconColor = gradient, // Цвет иконки для первой карточки
//            textColor = colorResource(id = R.color.orangeFour), // Цвет текста с количеством разделов для первой карточки
//            textBacColor = colorResource(id = R.color.orangeFourBacround)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        ModuleCard(
//            iconResId = R.drawable.packag,
//            title = "Управление складом",
//            description = "Залы, инвентаризация, основные средства",
//            sections = 6,
//            iconColor = gradient2, // Цвет иконки для второй карточки
//            textColor = colorResource(id = R.color.greenFour), // Цвет текста с количеством разделов для второй карточки
//            textBacColor = colorResource(id = R.color.greenFourBacround)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        ModuleCard(
//            iconResId = R.drawable.shoppingcart,
//            title = "Управление закупками",
//            description = "Документы закупок и номенклатура",
//            sections = 2,
//            iconColor = gradient3, // Цвет иконки для третьей карточки
//            textColor = colorResource(id = R.color.blueFour),// Цвет текста с количеством разделов для третьей карточки
//            textBacColor = colorResource(id = R.color.blueFourBacround)
//        )
//    }
//}
//
//@Composable
//fun ModuleCard(
//    iconResId: Int,
//    title: String,
//    description: String,
//    sections: Int,
//    iconColor: Brush,
//    textColor: Color,
//    textBacColor: Color
//) {
//    Card(
//        modifier = Modifier
//            .background(Color.White, shape = RoundedCornerShape(15.dp))
//            .fillMaxWidth(0.9F)
//            .height(140.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White) // Белый фон для содержимого внутри Card
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//                    .padding(20.dp) // Паддинг внутри Card
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(48.dp)
//                        .background(iconColor, shape = RoundedCornerShape(7.dp)) // Фон для картинки
//                ) {
//                    Image(
//                        painter = painterResource(id = iconResId),
//
//                        contentDescription = "Module Icon",
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(7.dp)
//
//
//                    )
//                }
//                Spacer(modifier = Modifier.width(16.dp))
//                Column {
//                    Text(
//                        text = title,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Text(
//                        text = description,
//                    )
//                    Box(
//                        modifier = Modifier
//                            .background(
//                                textBacColor,
//                                shape = RoundedCornerShape(15.dp)
//                            ) // Фон для текста с количеством разделов
//                            .padding(5.dp)
//                    ) {
//                        Text(
//                            text = if (sections == 1) "$sections раздел" else if (sections in 2..4) "$sections раздела" else "$sections разделов",
//                            color = textColor
//                        )
//                    }
//                }
//            }
//        }
//    }
//}



