package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PanduanScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // JUDUL
        Text(
            text = "Panduan Penggunaan Aplikasi",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CARD ISI PANDUAN
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            ),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                PanduanItem(
                    title = "1. Login & Registrasi",
                    content = "Pengguna harus login menggunakan nomor HP dan password. Jika belum memiliki akun, silakan lakukan registrasi."
                )

                PanduanItem(
                    title = "2. Lupa Password",
                    content = "Gunakan menu lupa password dan lakukan verifikasi OTP yang dikirim melalui WhatsApp, SMS, atau telepon."
                )

                PanduanItem(
                    title = "3. Menu Beranda",
                    content = "Beranda menampilkan fitur utama seperti Cari Data, Pengajuan Bantuan, Pengaduan, Agen di Sekitar, dan Riwayat."
                )

                PanduanItem(
                    title = "4. Cari Data",
                    content = "Fitur ini digunakan untuk mencari informasi penerima bantuan berdasarkan nama atau alamat."
                )

                PanduanItem(
                    title = "5. Pengajuan Bantuan",
                    content = "Isi formulir pengajuan bantuan dengan data yang benar dan unggah dokumen pendukung."
                )

                PanduanItem(
                    title = "6. Pengaduan",
                    content = "Gunakan fitur pengaduan untuk melaporkan dugaan penerima bantuan yang tidak tepat sasaran."
                )

                PanduanItem(
                    title = "7. Riwayat",
                    content = "Menu riwayat menampilkan status pengajuan dan pengaduan yang pernah dilakukan."
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // BUTTON KEMBALI
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Kembali",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PanduanItem(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f)
        )
    }
}
