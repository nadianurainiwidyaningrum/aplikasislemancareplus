package com.example.slemancareplus.ui.screens.agen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.slemancareplus.ui.viewmodel.AgenViewModel
import com.example.slemancareplus.ui.viewmodel.AgenWithDistance
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgenScreen() {

    val context = LocalContext.current
    val viewModel: AgenViewModel = viewModel()
    val agenList by viewModel.agenList.collectAsState(initial = emptyList())

    val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
                    loc?.let {
                        viewModel.loadAgen(it.latitude, it.longitude)
                    }
                }
            }
        }

    LaunchedEffect(Unit) {
        if (
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
                loc?.let {
                    viewModel.loadAgen(it.latitude, it.longitude)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agen di Sekitar") }
            )
        }
    ) { padding ->

        if (agenList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(agenList) { item ->
                    AgenCard(item)
                }
            }
        }
    }
}

@Composable
private fun AgenCard(item: com.example.slemancareplus.ui.viewmodel.AgenWithDistance) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val uri = Uri.parse(
                    "https://www.google.com/maps/search/?api=1&query=${item.agen.latitude},${item.agen.longitude}"
                )
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = item.agen.nama_agen,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.agen.alamat,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Jarak: %.2f km".format(item.jarakKm),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
@Composable
private fun AgenCardProfessional(item: AgenWithDistance) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val uri = Uri.parse(
                    "https://www.google.com/maps/search/?api=1&query=${item.agen.latitude},${item.agen.longitude}"
                )
                context.startActivity(Intent(Intent.ACTION_VIEW, uri))
            },
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Nama Agen
            Text(
                text = item.agen.nama_agen,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Alamat
            Text(
                text = item.agen.alamat,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(6.dp))

            // Jarak
            Text(
                text = "Jarak: %.2f km".format(item.jarakKm),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}
