package com.example.slemancareplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pengajuan")
data class Pengajuan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nik: String,          // FK ke User.nik
    val nama: String,
    val alamat: String,
    val jenisBantuan: String,
    val alasan: String,
    val dokumen: String,
    val status: String,
    val tanggal: String
)