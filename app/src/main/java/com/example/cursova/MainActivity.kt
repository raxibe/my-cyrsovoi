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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.Screen.AcceptanceDocuments
import com.example.cursova.Screen.AddAcceptanceDocument
import com.example.cursova.Screen.AddHall
import com.example.cursova.Screen.AddInventoryDocumentScreen

import com.example.cursova.Screen.AddNomenclature
import com.example.cursova.Screen.AddPurchaseDocument
import com.example.cursova.Screen.AddRepair
import com.example.cursova.Screen.AddRepairDocument
import com.example.cursova.Screen.AddRepairReturnDocument
import com.example.cursova.Screen.AddResponsible
import com.example.cursova.Screen.AddServiceCenter
import com.example.cursova.Screen.AddSupplier
import com.example.cursova.Screen.AddWriteOffDocument
import com.example.cursova.Screen.FixedAssets


import com.example.cursova.Screen.Hall
import com.example.cursova.Screen.InventoryDocumentDetailsScreen
import com.example.cursova.Screen.InventoryDocumentsScreen


import com.example.cursova.Screen.Nomenclature
import com.example.cursova.Screen.ProcurementManagement
import com.example.cursova.Screen.PurchaseDocuments
import com.example.cursova.Screen.Repair
import com.example.cursova.Screen.RepairDocument
import com.example.cursova.Screen.RepairManagement
import com.example.cursova.Screen.RepairReturnDocument
import com.example.cursova.Screen.Responsible
import com.example.cursova.Screen.Screens

import com.example.cursova.Screen.ServiceCenter
import com.example.cursova.Screen.Supplier
import com.example.cursova.Screen.WarehouseManagement
import com.example.cursova.Screen.WriteOffDocuments


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
                composable(Screens.AcceptanceDocuments .route) {
                    AcceptanceDocuments(navController = navController)
                }
                composable(Screens.AddAcceptanceDocument .route) {
                    AddAcceptanceDocument(navController = navController)
                }
                composable(Screens.FixedAssets .route) {
                    FixedAssets(navController = navController)
                }
                composable(Screens.WriteOffDocuments .route) {
                    WriteOffDocuments(navController = navController)
                }
                composable(Screens.AddWriteOffDocument .route) {
                    AddWriteOffDocument(navController = navController)
                }
                composable(Screens.AddRepairDocument .route) {
                    AddRepairDocument(navController = navController)
                }
                composable(Screens.RepairDocument .route) {
                    RepairDocument(navController = navController)
                }
                composable(Screens.RepairReturnDocument .route) {
                    RepairReturnDocument(navController = navController)
                }
                composable(Screens.AddRepairDocument.route) {
                    AddRepairDocument(navController = navController)
                }
                composable(
                    route = Screens.AddRepairReturnDocument.route,
                    arguments = listOf(navArgument("repairDocumentId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val repairDocumentId = backStackEntry.arguments?.getInt("repairDocumentId") ?: -1
                    AddRepairReturnDocument(
                        navController = navController,
                        repairDocumentId = repairDocumentId
                    )
                }

                composable(Screens.AddInventoryDocumentScreen.route) {
                    AddInventoryDocumentScreen(navController = navController)
                }
                composable(Screens.InventoryDocumentsScreen.route) {
                    InventoryDocumentsScreen(navController = navController)
                }

//                composable(
//                    route = Screens.InventoryDocumentDetails.route,
//                    arguments = listOf(navArgument("documentId") { type = NavType.IntType })
//                ) { backStackEntry ->
//                    val documentId = backStackEntry.arguments?.getInt("documentId") ?: -1
//                    InventoryDocumentDetailsScreen(
//                        navController = navController,
//                        documentId = documentId
//                    )
//                }





            }


        }
    }
}





