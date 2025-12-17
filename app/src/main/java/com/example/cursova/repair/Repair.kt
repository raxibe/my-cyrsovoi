package com.example.cursova.repair

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repair")
data class Repair(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)