package com.example.slemancareplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slemancareplus.data.local.entity.PenerimaBantuan

@Dao
interface PenerimaBantuanDao {

    @Insert
    suspend fun insertPenerima(penerima: PenerimaBantuan)

    @Query("SELECT * FROM penerima_bantuan")
    suspend fun getAllPenerima(): List<PenerimaBantuan>
}