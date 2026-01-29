package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.slemancareplus.data.model.RiwayatModel
import com.example.slemancareplus.ui.viewmodel.RiwayatViewModel

/* =========================
   MODEL (SESUAI DB)
   ========================= */
data class RiwayatItem(
    val id: Int,
    val judul: String,
    val status: String,
    val tanggal: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiwayatScreen(
    onBack: () -> Unit,
    viewModel: RiwayatViewModel = viewModel()
) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    val pengaduan by viewModel.riwayatPengaduan.collectAsState()
    val pengajuan by viewModel.riwayatPengajuan.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { viewModel.setTab(0) },
                    text = { Text("Pengaduan") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { viewModel.setTab(1) },
                    text = { Text("Pengajuan") }
                )
            }

            when (selectedTab) {
                0 -> RiwayatList(
                    data = pengaduan,
                    onDelete = { viewModel.hapusPengaduan(it) }
                )

                1 -> RiwayatList(
                    data = pengajuan,
                    onDelete = { viewModel.hapusPengajuan(it) }
                )
            }
        }
    }
}

/* =========================
   LIST RIWAYAT
   ========================= */
@Composable
fun RiwayatList(
    data: List<RiwayatModel>,
    onDelete: (Int) -> Unit
) {
    if (data.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Belum ada riwayat")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = data,
                key = { it.id }
            ) { item ->
                RiwayatCard(
                    item = item,
                    onDelete = { onDelete(item.id) }
                )
            }
        }
    }
}

/* =========================
   ITEM / CARD
   ========================= */
@Composable
fun RiwayatCard(
    item: RiwayatModel,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.judul,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text("Status: ${item.status}")
                Text(
                    text = item.tanggal,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Hapus"
                )
            }
        }
    }
}
