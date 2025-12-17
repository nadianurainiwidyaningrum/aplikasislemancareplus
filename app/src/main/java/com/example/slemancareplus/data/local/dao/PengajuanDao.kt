package com.example.slemancareplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slemancareplus.data.local.entity.Pengajuan

@Dao
interface PengajuanDao {

    @Insert
    suspend fun insertPengajuan(pengajuan: Pengajuan)

    @Query("SELECT * FROM pengajuan")
    suspend fun getAllPengajuan(): List<Pengajuan>
}