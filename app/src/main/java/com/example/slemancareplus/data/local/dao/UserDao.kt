package com.example.slemancareplus.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slemancareplus.data.local.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>
}
