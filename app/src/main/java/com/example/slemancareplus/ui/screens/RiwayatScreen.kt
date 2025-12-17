package com.example.slemancareplus.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

/* =========================
   MODEL & ENUM
   ========================= */

enum class JenisRiwayat {
    PENGAJUAN,
    PENERIMAAN,
    PENGADUAN
}

data class RiwayatItem(
    val jenis: JenisRiwayat,
    val judul: String,
    val tanggal: String,
    val deskripsi: String,
    val status: String
)

/* =========================
   SCREEN
   ========================= */

@Composable
fun RiwayatScreen(navController: NavController) {
    val context = LocalContext.current

    val riwayatList = listOf(
        RiwayatItem(
            jenis = JenisRiwayat.PENGAJUAN,
            judul = "Pengajuan Bantuan PKH",
            tanggal = "10 Mei 2025",
            deskripsi = "Pengajuan bantuan Program Keluarga Harapan",
            status = "Sedang Diproses"
        ),
        RiwayatItem(
            jenis = JenisRiwayat.PENERIMAAN,
            judul = "Penerimaan BLT",
            tanggal = "20 April 2025",
            deskripsi = "Bantuan BLT telah diterima",
            status = "Diterima"
        ),
        RiwayatItem(
            jenis = JenisRiwayat.PENGADUAN,
            judul = "Pengaduan Bantuan Tidak Tepat Sasaran",
            tanggal = "5 April 2025",
            deskripsi = "Laporan dugaan penerima bantuan tidak layak",
            status = "Selesai"
        )
    )

    var selectedFilter by remember { mutableStateOf<JenisRiwayat?>(null) }

    val filteredList =
        if (selectedFilter == null) riwayatList
        else riwayatList.filter { it.jenis == selectedFilter }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // JUDUL
        Text(
            text = "Riwayat",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        // FILTER
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilterButton("Semua", selectedFilter == null) {
                selectedFilter = null
            }
            FilterButton("Pengajuan", selectedFilter == JenisRiwayat.PENGAJUAN) {
                selectedFilter = JenisRiwayat.PENGAJUAN
            }
            FilterButton("Penerimaan", selectedFilter == JenisRiwayat.PENERIMAAN) {
                selectedFilter = JenisRiwayat.PENERIMAAN
            }
            FilterButton("Pengaduan", selectedFilter == JenisRiwayat.PENGADUAN) {
                selectedFilter = JenisRiwayat.PENGADUAN
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (filteredList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada riwayat",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn {
                items(filteredList) { item ->
                    RiwayatCard(
                        item = item,
                        onShare = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "${item.judul}\nTanggal: ${item.tanggal}\nStatus: ${item.status}"
                                )
                            }
                            context.startActivity(
                                Intent.createChooser(shareIntent, "Bagikan ke")
                            )
                        },
                        onDownload = {
                            Toast.makeText(
                                context,
                                "Download belum tersedia",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

/* =========================
   COMPONENTS
   ========================= */

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor =
            if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface,
            contentColor =
            if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.primary
        ),
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun RiwayatCard(
    item: RiwayatItem,
    onShare: () -> Unit,
    onDownload: () -> Unit
) {
    val statusColor = when (item.status.lowercase()) {
        "diterima" -> Color(0xFF4CAF50)
        "sedang diproses" -> Color(0xFFFFC107)
        "selesai", "ditolak" -> Color(0xFFF44336)
        else -> MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {

            Text(
                text = item.judul,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )

            Text(
                text = item.tanggal,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.deskripsi,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Status: ${item.status}",
                fontWeight = FontWeight.SemiBold,
                color = statusColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onShare) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Bagikan",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDownload) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
