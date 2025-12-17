package com.example.slemancareplus.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "riwayat")
data class Riwayat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val jenis: String,        // PENGAJUAN / PENGADUAN / PENERIMAAN
    val judul: String,
    val tanggal: String,
    val deskripsi: String,
    val status: String
)