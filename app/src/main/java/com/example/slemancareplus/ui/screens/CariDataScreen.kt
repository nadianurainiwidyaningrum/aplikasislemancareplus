package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.slemancareplus.data.model.DataWarga
import com.example.slemancareplus.ui.viewmodel.CariDataViewModel


@Composable
fun NavHostController.CariDataScreen(
    viewModel: CariDataViewModel = viewModel()
) {
    var keyword by remember { mutableStateOf("") }
    val hasil = viewModel.result

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Cari Data Warga",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = keyword,
            onValueChange = { keyword = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Masukkan nama atau alamat") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (keyword.isNotBlank()) {
                    viewModel.cari(keyword)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cari")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (hasil.isEmpty()) {
            Text(
                text = "Data tidak ditemukan",
                color = MaterialTheme.colorScheme.error
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(hasil) { warga ->
                    WargaCard(warga)
                }
            }
        }
    }
}

/* =========================
   CARD DATA WARGA
   ========================= */

@Composable
fun WargaCard(warga: DataWarga) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = warga.nama,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            DetailRow("NIK", warga.nik)
            DetailRow("Alamat", warga.alamat)
            DetailRow("Gaji", warga.gaji)
            DetailRow("Jumlah Anak", warga.jumlah_anak.toString())
            DetailRow("Kendaraan", warga.kendaraan)
            DetailRow("Status Rumah", warga.status_rumah)
        }
    }
}

/* =========================
   ROW DETAIL
   ========================= */

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1.2f),
            fontWeight = FontWeight.SemiBold
        )
        Text(text = ": ")
        Text(
            text = value,
            modifier = Modifier.weight(2f)
        )
    }
}
