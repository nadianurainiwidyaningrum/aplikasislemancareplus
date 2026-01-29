package com.example.slemancareplus.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slemancareplus.api.ApiClient
import com.example.slemancareplus.model.AgenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.*

data class AgenWithDistance(
    val agen: AgenModel,
    val jarakKm: Double
)

class AgenViewModel : ViewModel() {

    private val _agenList = MutableStateFlow<List<AgenWithDistance>>(emptyList())
    val agenList: StateFlow<List<AgenWithDistance>> = _agenList

    fun loadAgen(userLat: Double, userLng: Double) {
        viewModelScope.launch {
            try {
                val res = ApiClient.api.getAgen()
                if (res.isSuccessful) {
                    val data = res.body() ?: emptyList()
                    _agenList.value = data.map { agen ->
                        AgenWithDistance(
                            agen = agen,
                            jarakKm = hitungJarak(
                                userLat, userLng,
                                agen.latitude, agen.longitude
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun hitungJarak(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val r = 6371
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) *
                cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }
}
