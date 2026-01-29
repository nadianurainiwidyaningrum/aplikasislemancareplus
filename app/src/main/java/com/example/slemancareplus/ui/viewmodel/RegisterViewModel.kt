package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.RegisterRequest
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)

    var success by mutableStateOf(false)
        private set

    fun register(
        nik: String,
        nama: String,
        phone: String,
        password: String
    ) {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                val response = RetrofitClient.api.register(
                    RegisterRequest(nik, nama, phone, password)
                )

                if (response.isSuccessful) {
                    success = true
                } else {
                    error = response.message()
                }

            } catch (e: Exception) {
                error = "Gagal terhubung ke server"
            } finally {
                isLoading = false
            }
        }
    }
}
