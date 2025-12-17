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

@Composable
fun PengaduanScreen(navController: NavController) {

    var namaTerduga by remember { mutableStateOf("") }
    var dukuh by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var kronologi by remember { mutableStateOf("") }

    var bukti by remember { mutableStateOf<Uri?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> bukti = uri }

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

        // JUDUL
        Text(
            text = "Pengaduan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FORM
        OutlinedTextField(
            value = namaTerduga,
            onValueChange = { namaTerduga = it; error = null },
            label = { Text("Nama Terduga *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dukuh,
            onValueChange = { dukuh = it; error = null },
            label = { Text("Nama Dukuh Terduga *") },
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

        OutlinedTextField(
            value = noHp,
            onValueChange = { noHp = it; error = null },
            label = { Text("No HP *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = kronologi,
            onValueChange = { kronologi = it; error = null },
            label = { Text("Kronologi Kejadian *") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // UPLOAD BUKTI
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            )
        ) {
            Text(
                text = if (bukti == null)
                    "Upload Bukti (Opsional)"
                else
                    "Bukti Terpilih",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "* Bukti bersifat opsional",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

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
                    error = "Semua data wajib diisi dengan benar"
                } else {
                    navController.navigate("${Routes.THANKYOU}/pengaduan")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Kirim Pengaduan",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
