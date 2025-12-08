package com.example.cursova.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cursova.Hall.Hall
import com.example.cursova.Hall.HallDao
import com.example.cursova.Nomenclature.Nomenclature
import com.example.cursova.Nomenclature.NomenclatureDao
import com.example.cursova.Responsible.Responsible
import com.example.cursova.Responsible.ResponsibleDao

import com.example.cursova.ServiceCenter.ServiceCenter
import com.example.cursova.ServiceCenter.ServiceCenterDao
import com.example.cursova.Supplier.Supplier
import com.example.cursova.Supplier.SupplierDao

import com.example.cursova.purchase.PurchaseDocument
import com.example.cursova.purchase.PurchaseDocumentDao
import com.example.cursova.repair.Repair
import com.example.cursova.repair.RepairDao



@Database(
    entities = [Nomenclature::class, Hall::class, Supplier::class,
        Repair::class, ServiceCenter:: class, Responsible::class,
        PurchaseDocument::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun nomentDao(): NomenclatureDao
    abstract fun hallDao(): HallDao
    abstract fun supplierDao(): SupplierDao
    abstract fun repairDao(): RepairDao
    abstract fun serviceCenterDao(): ServiceCenterDao
    abstract fun responsibleDao(): ResponsibleDao
    abstract fun purchaseDocumentDao(): PurchaseDocumentDao


    companion object {
        const val DATABASE_NAME = "app_database"
    }
}