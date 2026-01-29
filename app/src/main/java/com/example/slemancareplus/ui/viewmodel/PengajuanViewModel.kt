package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.PengajuanRequest
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class PengajuanViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun kirimPengajuan(
        nik: String,
        nama: String,
        alamat: String,
        jenisBantuan: String,
        alasan: String,
        dokumenUri: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val response = RetrofitClient.api.kirimPengajuan(
                    PengajuanRequest(
                        nik = nik,
                        nama = nama,
                        alamat = alamat,
                        jenis_bantuan = jenisBantuan,
                        alasan = alasan,
                        dokumen = dokumenUri
                    )
                )

                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    errorMessage = response.message()
                }
            } catch (e: Exception) {
                errorMessage = "Gagal mengirim pengajuan"
            } finally {
                isLoading = false
            }
        }
    }
}
