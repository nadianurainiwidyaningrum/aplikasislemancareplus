package com.example.slemancareplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.slemancareplus.data.model.DataWarga
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class CariDataViewModel : ViewModel() {

    var result by mutableStateOf<List<DataWarga>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    fun cari(keyword: String) {
        viewModelScope.launch {
            isLoading = true
            message = null

            try {
                val response = RetrofitClient.api.cariData(keyword)

                if (response.isSuccessful) {
                    result = response.body()?.data ?: emptyList()
                } else {
                    result = emptyList()
                    message = "Gagal mengambil data"
                }

            } catch (e: Exception) {
                result = emptyList()
                message = "Koneksi bermasalah"
            } finally {
                isLoading = false
            }
        }
    }
}
