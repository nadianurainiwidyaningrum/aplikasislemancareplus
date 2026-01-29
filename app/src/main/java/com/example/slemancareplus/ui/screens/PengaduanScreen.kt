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
import com.example.slemancareplus.ui.viewmodel.PengaduanViewModel

@Composable
fun PengaduanScreen(
    navController: NavController,
    viewModel: PengaduanViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    var namaTerduga by remember { mutableStateOf("") }
    var dukuh by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var kronologi by remember { mutableStateOf("") }

    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    val formValid =
        namaTerduga.isNotBlank() &&
                dukuh.isNotBlank() &&
                alamat.isNotBlank() &&
                noHp.length >= 10 &&
                kronologi.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Pengaduan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = namaTerduga,
            onValueChange = { namaTerduga = it },
            label = { Text("Nama Terduga *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dukuh,
            onValueChange = { dukuh = it },
            label = { Text("Nama Dukuh Terduga *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alamat,
            onValueChange = { alamat = it },
            label = { Text("Alamat *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = noHp,
            onValueChange = { noHp = it },
            label = { Text("No HP *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = kronologi,
            onValueChange = { kronologi = it },
            label = { Text("Kronologi Kejadian *") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (error != null) {
            Text(error!!, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                if (formValid) {
                    viewModel.kirimPengaduan(
                        namaTerduga,
                        dukuh,
                        alamat,
                        noHp,
                        kronologi
                    ) {
                        navController.navigate("${Routes.THANKYOU}/pengaduan")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        ) {
            Text(if (loading) "Mengirim..." else "Kirim Pengaduan")
        }
    }
}
