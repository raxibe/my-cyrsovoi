package com.example.cursova.ServiceCenter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service_centers")
data class ServiceCenter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)