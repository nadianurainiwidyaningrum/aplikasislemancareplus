package com.example.slemancareplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agen")
data class Agen(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val alamat: String,
    val jarak: Double,
    val latitude: Double,
    val longitude: Double
)