package com.example.cursova.Responsible

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responsibles")
data class Responsible(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val hallId: Int // ID зала, за который отвечает ответственный
)