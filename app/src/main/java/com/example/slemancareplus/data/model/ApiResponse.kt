package com.example.slemancareplus.data.model

data class ApiResponse<T>(
    val status: Boolean,
    val message: String,
    val data: T?
)

