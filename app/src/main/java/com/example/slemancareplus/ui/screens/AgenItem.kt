package com.example.slemancareplus.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.slemancareplus.ui.viewmodel.AgenWithDistance
import java.util.Locale

@Composable
fun AgenItem(item: AgenWithDistance) {
    val context = LocalContext.current
    val agen = item.agen

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable {
                val uri = Uri.parse(
                    "https://www.google.com/maps/search/?api=1&query=${agen.latitude},${agen.longitude}"
                )
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = agen.nama_agen,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = agen.alamat,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = String.format(
                    Locale.US,
                    "Jarak: %.2f km",
                    item.jarakKm
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
