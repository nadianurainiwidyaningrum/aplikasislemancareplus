package com.example.slemancareplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slemancareplus.data.local.entity.Riwayat

@Dao
interface RiwayatDao {

    @Insert
    suspend fun insertRiwayat(riwayat: Riwayat)

    @Query("SELECT * FROM riwayat")
    suspend fun getAllRiwayat(): List<Riwayat>
}