package com.example.slemancareplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.RiwayatModel
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RiwayatViewModel : ViewModel() {

    /* ================= TAB ================= */
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab

    fun setTab(tab: Int) {
        _selectedTab.value = tab
    }

    /* ================= DATA ================= */
    private val _riwayatPengaduan = MutableStateFlow<List<RiwayatModel>>(emptyList())
    val riwayatPengaduan: StateFlow<List<RiwayatModel>> = _riwayatPengaduan

    private val _riwayatPengajuan = MutableStateFlow<List<RiwayatModel>>(emptyList())
    val riwayatPengajuan: StateFlow<List<RiwayatModel>> = _riwayatPengajuan

    fun loadPengajuan(nik: String) {
        viewModelScope.launch {
            try {
                val res = RetrofitClient.api.getPengajuan(nik)
                if (res.isSuccessful) {
                    _riwayatPengajuan.value = res.body()?.data ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadPengaduan(nik: String) {
        viewModelScope.launch {
            try {
                val res = RetrofitClient.api.getPengaduan(nik)
                if (res.isSuccessful) {
                    _riwayatPengaduan.value = res.body()?.data ?: emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /* ================= DELETE ================= */
    fun hapusPengajuan(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.deletePengajuan(id)
                _riwayatPengajuan.value =
                    _riwayatPengajuan.value.filter { it.id != id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun hapusPengaduan(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.deletePengaduan(id)
                _riwayatPengaduan.value =
                    _riwayatPengaduan.value.filter { it.id != id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
