package com.example.slemancareplus.ui.viewmodel

import androidx.lifecycle.ViewModel

class PengaturanViewModel : ViewModel() {

    fun logout(onSuccess: () -> Unit) {
        // 🔥 nanti di sini bisa:
        // - hapus token
        // - clear SharedPreferences / DataStore
        // - call API logout kalau ada

        onSuccess()
    }
}
