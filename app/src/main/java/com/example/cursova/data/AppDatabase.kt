package com.example.cursova.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cursova.AcceptanceDocument.AcceptanceDocument
import com.example.cursova.AcceptanceDocument.AcceptanceDocumentDao
import com.example.cursova.FixedAsset.FixedAsset
import com.example.cursova.FixedAsset.FixedAssetDao
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
import com.example.cursova.WriteOffDocument.WriteOffDocument
import com.example.cursova.WriteOffDocument.WriteOffDocumentDao
import com.example.cursova.purchase.Item
import com.example.cursova.purchase.ItemDao

import com.example.cursova.purchase.PurchaseDocument
import com.example.cursova.purchase.PurchaseDocumentDao
import com.example.cursova.repair.Repair
import com.example.cursova.repair.RepairDao


@Database(
    entities = [Nomenclature::class, Hall::class, Supplier::class,
        Repair::class, ServiceCenter::class, Responsible::class,
        PurchaseDocument::class, Item::class, AcceptanceDocument::class,
        FixedAsset::class,
        WriteOffDocument::class
               ],
    version = 2,
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
    abstract fun itemDao(): ItemDao
    abstract fun acceptanceDocumentDao(): AcceptanceDocumentDao
    abstract fun fixedAssetDao(): FixedAssetDao
    abstract fun writeOffDocumentDao(): WriteOffDocumentDao


    companion object {
        const val DATABASE_NAME = "app_database"



    }








}


