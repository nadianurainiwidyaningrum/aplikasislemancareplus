package com.example.slemancareplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slemancareplus.data.local.entity.Pengaduan

@Dao
interface PengaduanDao {

    @Insert
    suspend fun insertPengaduan(pengaduan: Pengaduan)

    @Query("SELECT * FROM pengaduan")
    suspend fun getAllPengaduan(): List<Pengaduan>
}