package com.example.slemancareplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slemancareplus.data.local.entity.Agen

@Dao
interface AgenDao {

    @Insert
    suspend fun insertAgen(agen: Agen)

    @Query("SELECT * FROM agen")
    suspend fun getAllAgen(): List<Agen>
}