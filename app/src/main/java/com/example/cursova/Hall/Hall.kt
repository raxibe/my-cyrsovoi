package com.example.cursova.Hall

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "halls")
data class Hall(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)