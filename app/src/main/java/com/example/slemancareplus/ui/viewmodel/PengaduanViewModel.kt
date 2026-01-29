package com.example.slemancareplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.PengaduanRequest
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PengaduanViewModel : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun kirimPengaduan(
        namaTerduga: String,
        dukuh: String,
        alamat: String,
        noHp: String,
        kronologi: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                _loading.value = true

                val response = RetrofitClient.api.kirimPengaduan(
                    PengaduanRequest(
                        nama_terduga = namaTerduga,
                        dukuh = dukuh,
                        alamat = alamat,
                        no_hp = noHp,
                        kronologi = kronologi
                    )
                )

                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    _error.value = response.message()
                }

            } catch (e: Exception) {
                _error.value = "Gagal mengirim pengaduan"
            } finally {
                _loading.value = false
            }
        }
    }
}
