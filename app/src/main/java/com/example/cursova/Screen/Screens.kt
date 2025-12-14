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
    object PurchaseDocuments : Screens ("purchase-documents")
    object AddPurchaseDocuments : Screens ("purchase-documents-add")
    object AcceptanceDocuments : Screens ("acceptance-documents")
    object AddAcceptanceDocument : Screens ("acceptance-document-add")
    object FixedAssets : Screens ("fixed-assets")
    object WriteOffDocuments : Screens ("write-off-documents")
    object AddWriteOffDocument : Screens ("write-off-document-add")
    object AddRepairDocument : Screens ("repair-document-add")
    object RepairDocument : Screens ("repair-document")
    object RepairReturnDocument : Screens ("repair-return-document")
    object AddRepairReturnDocument : Screens("add_repair_return_document/{repairDocumentId}") {
        fun createRoute(repairDocumentId: Int) = "add_repair_return_document/$repairDocumentId"
    }

}