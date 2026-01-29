package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.PemberdayaanModel
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class PemberdayaanViewModel : ViewModel() {

    var list by mutableStateOf<List<PemberdayaanModel>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    fun loadData() {
        viewModelScope.launch {
            isLoading = true
            message = null

            try {
                val response = RetrofitClient.api.getPemberdayaan()

                if (response.isSuccessful) {
                    list = response.body()?.data ?: emptyList()
                } else {
                    message = "Gagal memuat data"
                }

            } catch (e: Exception) {
                message = "Gagal terhubung ke server"
            } finally {
                isLoading = false
            }
        }
    }
}
