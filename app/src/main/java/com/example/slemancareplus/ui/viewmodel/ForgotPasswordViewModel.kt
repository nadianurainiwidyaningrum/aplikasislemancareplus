package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class ForgotPasswordViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    var isSuccess by mutableStateOf(false)
        private set

    fun sendOtp(username: String) {
        viewModelScope.launch {
            isLoading = true
            message = null
            isSuccess = false

            try {
                val response = RetrofitClient.api.forgotPassword(username)

                if (response.isSuccessful) {
                    val body = response.body()

                    if (body?.status == true) {
                        isSuccess = true
                    }

                    message = body?.message
                } else {
                    message = "Gagal mengirim OTP"
                }

            } catch (e: Exception) {
                message = "Gagal terhubung ke server"
            } finally {
                isLoading = false
            }
        }
    }
}
