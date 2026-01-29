package com.example.slemancareplus.data.model

import com.google.gson.annotations.SerializedName

data class ProfileModel(
    val id: String,
    val nama: String,

    @SerializedName("no_hp")
    val noHp: String,

    val alamat: String,
    val no_hp: String
)
