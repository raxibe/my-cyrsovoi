package com.example.cursova.di

import android.content.Context
import androidx.room.Room
import com.example.cursova.data.AppDatabase
import com.example.cursova.data.NomenclatureDao
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
    fun provideNomenclatureDao(database: AppDatabase): NomenclatureDao {
        return database.nomenclatureDao()
    }
}