package com.example.slemancareplus.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResetPasswordViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    fun resetPassword(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _message.value = null

            try {
                val response = RetrofitClient.api.resetPassword(
                    username = username,
                    password = password
                )

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    if (body.status) {
                        _isSuccess.value = true
                    } else {
                        _message.value = body.message
                    }
                } else {
                    _message.value = "Gagal reset password"
                }

            } catch (e: Exception) {
                _message.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
