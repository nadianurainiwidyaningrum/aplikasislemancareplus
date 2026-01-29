package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformasiScreen(navController: NavHostController) {

    val informasiList = listOf(
        InformasiItem(
            judul = "Pendaftaran Bantuan Sosial",
            isi = "Pendaftaran bantuan sosial dibuka hingga akhir bulan. Pastikan data yang dimasukkan sudah sesuai."
        ),
        InformasiItem(
            judul = "Verifikasi Data Penerima",
            isi = "Proses verifikasi dilakukan oleh petugas kelurahan sebelum bantuan disalurkan."
        ),
        InformasiItem(
            judul = "Penyaluran Bantuan",
            isi = "Bantuan akan disalurkan melalui agen resmi yang telah bekerja sama dengan pemerintah daerah."
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Informasi Bantuan",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(informasiList) { info ->
                InformasiCard(info)
            }
        }
    }
}

/* =======================
   INFORMASI CARD
   ======================= */

@Composable
fun InformasiCard(item: InformasiItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.judul,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.isi,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/* =======================
   DATA MODEL
   ======================= */

data class InformasiItem(
    val judul: String,
    val isi: String
)
