package com.example.cursova.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nomenclature")
data class Nomenclature (
    @PrimaryKey(autoGenerate = true)  val id: Long = 0,
    val name: String,
    val type : String

)