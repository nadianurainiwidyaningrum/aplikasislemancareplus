package com.example.slemancareplus.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.data.model.ProfileModel
import com.example.slemancareplus.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    var profile by mutableStateOf<ProfileModel?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf<String?>(null)
        private set

    fun loadProfile(userId: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val res = RetrofitClient.api.getProfile(userId)
                if (res.isSuccessful) {
                    profile = res.body()?.data
                } else {
                    message = res.message()
                }
            } catch (e: Exception) {
                message = "Gagal memuat profil"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateProfile(
        userId: String,
        nama: String,
        noHp: String,
        alamat: String
    ) {
        viewModelScope.launch {
            isLoading = true
            try {
                val res = RetrofitClient.api.updateProfile(
                    userId, nama, noHp, alamat
                )
                message = if (res.isSuccessful) {
                    res.body()?.message
                } else {
                    res.message()
                }
            } catch (e: Exception) {
                message = "Gagal update profil"
            } finally {
                isLoading = false
            }
        }
    }
}
