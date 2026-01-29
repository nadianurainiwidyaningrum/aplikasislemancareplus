package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.PanduanModel
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class PanduanViewModel : ViewModel() {

    var panduan by mutableStateOf<List<PanduanModel>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    fun loadPanduan() {
        viewModelScope.launch {
            isLoading = true
            message = null

            try {
                val response = RetrofitClient.api.getPanduan()

                if (response.isSuccessful) {
                    panduan = response.body()?.data ?: emptyList()
                } else {
                    message = "Gagal memuat panduan"
                }

            } catch (e: Exception) {
                message = "Gagal terhubung ke server"
            } finally {
                isLoading = false
            }
        }
    }
}
