package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfilScreen(
    navController: NavController
) {

    // DATA STATIS (NANTI BISA DARI DATABASE / API)
    val nama = "Siti Aminah"
    val nik = "3404123456789012"
    val noHp = "081234567890"
    val alamat = "Jl. Kaliurang, Sleman"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // JUDUL
        Text(
            text = "Profil Pengguna",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfilItem(label = "Nama Lengkap", value = nama)
        ProfilItem(label = "NIK", value = nik)
        ProfilItem(label = "Nomor HP", value = noHp)
        ProfilItem(label = "Alamat", value = alamat)

        Spacer(modifier = Modifier.height(32.dp))

        // TOMBOL EDIT PROFIL
        Button(
            onClick = {
                // navController.navigate(Routes.EDIT_PROFIL)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Profil",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Edit Profil",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProfilItem(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {

        Text(
            text = label,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(6.dp))

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
    }
}
