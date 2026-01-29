package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes
import com.example.slemancareplus.ui.viewmodel.ProfileViewModel
import com.example.slemancareplus.ui.viewmodel.PengaturanViewModel

@Composable
fun PengaturanScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel(),
    pengaturanViewModel: PengaturanViewModel = viewModel()
) {

    val profile = profileViewModel.profile

    var nama by remember { mutableStateOf("") }
    var noHp by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile(userId = "1")
    }

    LaunchedEffect(profile) {
        profile?.let {
            nama = it.nama
            noHp = it.no_hp
            alamat = it.alamat
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Pengaturan Akun",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Data Profil", fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = noHp,
            onValueChange = { noHp = it },
            label = { Text("Nomor HP") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = alamat,
            onValueChange = { alamat = it },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                profileViewModel.updateProfile(
                    userId = "1",
                    nama = nama,
                    noHp = noHp,
                    alamat = alamat
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Profil")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Divider()

        Spacer(modifier = Modifier.height(24.dp))

        Text("Keamanan", fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                navController.navigate(Routes.RESET)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ganti Kata Sandi")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Divider()

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                pengaturanViewModel.logout {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Keluar")
        }
    }
}
