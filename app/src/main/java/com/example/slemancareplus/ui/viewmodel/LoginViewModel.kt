package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)

    var success by mutableStateOf(false)
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            error = null
            success = false

            try {
                val response = RetrofitClient.api.login(username, password)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body?.status == true) {
                        success = true
                    } else {
                        error = body?.message ?: "Login gagal"
                    }
                } else {
                    error = "Login gagal"
                }

            } catch (e: Exception) {
                error = "Gagal terhubung ke server"
            } finally {
                isLoading = false
            }
        }
    }
}
