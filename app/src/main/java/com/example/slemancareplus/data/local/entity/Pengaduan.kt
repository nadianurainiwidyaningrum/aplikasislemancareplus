package com.example.slemancareplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengaduan")
data class Pengaduan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nik: String,          // FK ke User.nik
    val nama: String,
    val kategori: String,
    val dukuh: String,
    val alamat: String,
    val kronologi: String,
    val bukti: String,
    val status: String,
    val tanggal: String
)