package com.example.cursova.Screen

sealed class Screens (val route: String){
    object FirstScreen : Screens("first_screen")
    object RepairManagment : Screens("repair-man")
    object WarehouseManagement : Screens ("warehouse-management")
    object ProcurementManagement : Screens ("procurement-management")
    object Nomenclature : Screens ("nomenclature")
}