package com.example.slemancareplus.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PengajuanScreen(navController: NavController) {

    var nik by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenisBantuan by remember { mutableStateOf("") }
    var alasan by remember { mutableStateOf("") }

    var dokumen by remember { mutableStateOf<Uri?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val bantuanList = listOf(
        "PKH",
        "BLT",
        "Beasiswa Sleman Pintar"
    )

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        dokumen = uri
        error = null
    }

    val formValid =
        nik.length == 16 &&
                nama.isNotBlank() &&
                alamat.isNotBlank() &&
                jenisBantuan.isNotBlank() &&
                alasan.isNotBlank() &&
                dokumen != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // JUDUL
        Text(
            text = "Pengajuan Bantuan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FORM INPUT
        OutlinedTextField(
            value = nik,
            onValueChange = { nik = it; error = null },
            label = { Text("NIK *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it; error = null },
            label = { Text("Nama Lengkap *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alamat,
            onValueChange = { alamat = it; error = null },
            label = { Text("Alamat *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // DROPDOWN JENIS BANTUAN
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = jenisBantuan,
                onValueChange = {},
                readOnly = true,
                label = { Text("Jenis Bantuan *") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                bantuanList.forEach { bantuan ->
                    DropdownMenuItem(
                        text = { Text(bantuan) },
                        onClick = {
                            jenisBantuan = bantuan
                            expanded = false
                            error = null
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alasan,
            onValueChange = { alasan = it; error = null },
            label = { Text("Alasan Pengajuan *") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // UPLOAD DOKUMEN
        Button(
            onClick = { launcher.launch("*/*") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        ) {
            Text(
                text = if (dokumen == null)
                    "Upload KK / KTP / Dokumen Pendukung *"
                else
                    "Dokumen berhasil dipilih",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ERROR
        if (error != null) {
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // SUBMIT
        Button(
            onClick = {
                if (!formValid) {
                    error = "Semua data wajib diisi termasuk dokumen pendukung"
                } else {
                    navController.navigate("${Routes.THANKYOU}/pengajuan")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Kirim Pengajuan",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
