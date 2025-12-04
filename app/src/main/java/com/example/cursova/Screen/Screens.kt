package com.example.cursova.Screen

sealed class Screens (val route: String){
    object FirstScreen : Screens("first_screen")
    object RepairManagment : Screens("repair-man")
    object WarehouseManagement : Screens ("warehouse-management")
    object ProcurementManagement : Screens ("procurement-management")
    object Nomenclature : Screens ("nomenclature")
    object AddNomenclature : Screens ("add-nomen")
    object Hall : Screens ("hall")
    object AddHall : Screens ("hall-add")
    object Supplier : Screens ("supplier")
    object AddSupplier : Screens ("supplier-add")
    object Repair : Screens ("repair")
    object AddRepair : Screens ("repair-add")
    object ServiceCenter : Screens ("service-center")
    object AddServiceCenter : Screens ("service-center-add")
    object Responsible : Screens ("responsible")
    object AddResponsible : Screens ("responsible-add")
}