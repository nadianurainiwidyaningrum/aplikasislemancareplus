package com.example.slemancareplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nik: String,
    val nama: String,
    val noHp: String,
    val password: String,
    val alamat: String
)
