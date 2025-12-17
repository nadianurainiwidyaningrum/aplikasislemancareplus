package com.example.slemancareplus.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.*

/* DATA MODEL AGEN */
data class Agen(
    val nama: String,
    val alamat: String,
    val lat: Double,
    val lon: Double
)

@Composable
fun AgenScreen(navController: NavController) {
    val context = LocalContext.current

    // LOKASI USER (DUMMY – SLEMAN)
    val userLat = -7.71556
    val userLon = 110.35556

    // DATA AGEN (STATIS)
    val agenList = listOf(
        Agen("Agen Sleman Utara", "Jl. Kaliurang Km 10, Sleman", -7.7045, 110.3612),
        Agen("Agen Sleman Tengah", "Jl. Magelang, Sleman", -7.7496, 110.3552),
        Agen("Agen Sleman Selatan", "Jl. Godean, Sleman", -7.7812, 110.3278)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Agen di Sekitar Anda",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(agenList) { agen ->
                val jarak = hitungJarak(userLat, userLon, agen.lat, agen.lon)

                AgenCard(
                    agen = agen,
                    jarak = jarak,
                    onClick = {
                        val uri = Uri.parse(
                            "geo:${agen.lat},${agen.lon}?q=${agen.lat},${agen.lon}(${agen.nama})"
                        )
                        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                            setPackage("com.google.android.apps.maps")
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun AgenCard(
    agen: Agen,
    jarak: Double,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = agen.nama,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = agen.alamat,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Jarak: ${"%.2f".format(jarak)} km",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

/* RUMUS JARAK (HAVERSINE)*/
fun hitungJarak(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val radiusBumi = 6371.0 // KM
    val dLat = (lat2 - lat1).toRadians()
    val dLon = (lon2 - lon1).toRadians()

    val a = sin(dLat / 2).pow(2.0) +
            cos(lat1.toRadians()) * cos(lat2.toRadians()) *
            sin(dLon / 2).pow(2.0)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return radiusBumi * c
}

fun Double.toRadians() = this * PI / 180
