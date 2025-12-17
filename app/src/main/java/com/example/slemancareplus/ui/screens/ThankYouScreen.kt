package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ThankYouScreen(
    navController: NavController,
    message: String = "Pengaduan Telah Diterima!"
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Success",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Silakan cek menu Riwayat untuk melihat status terbaru pengajuan atau pengaduan Anda.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.65f)
            ) {
                Text("Kembali ke Beranda")
            }
        }
    }
}
