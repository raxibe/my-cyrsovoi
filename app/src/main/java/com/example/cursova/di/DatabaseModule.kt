package com.example.cursova.di

import android.content.Context
import androidx.room.Room
import com.example.cursova.data.AppDatabase
import com.example.cursova.Hall.HallDao
import com.example.cursova.Nomenclature.NomenclatureDao
import com.example.cursova.Responsible.ResponsibleDao
import com.example.cursova.ServiceCenter.ServiceCenterDao
import com.example.cursova.Supplier.SupplierDao
import com.example.cursova.repair.RepairDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideStudentDao(database: AppDatabase): NomenclatureDao {
        return database.nomentDao()
    }
    @Provides
    fun provideHallDao(database: AppDatabase): HallDao {
        return database.hallDao()
    }

    @Provides
    fun provideSupplierDao(database: AppDatabase): SupplierDao {
        return database.supplierDao()
    }

    @Provides
    fun provideRepairDao(database: AppDatabase): RepairDao {
        return database.repairDao()
    }

    @Provides
    fun provideServiceCenterDao(database: AppDatabase): ServiceCenterDao {
        return database.serviceCenterDao()
    }

    @Provides
    fun provideResponsibleDao(database: AppDatabase): ResponsibleDao {
        return database.responsibleDao()
    }
}