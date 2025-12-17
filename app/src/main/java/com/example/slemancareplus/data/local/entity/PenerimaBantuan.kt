package com.example.slemancareplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "penerima_bantuan")
data class PenerimaBantuan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nik: String,
    val nama: String,
    val alamat: String,
    val jumlahAnak: Int,
    val kendaraan: String,
    val jenjangSekolahAnak: String,
    val jenjangSekolahOrtu: String,
    val statusRumah: String,
    val rangeGaji: String
)
