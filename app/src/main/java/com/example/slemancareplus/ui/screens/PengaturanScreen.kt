package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes

@Composable
fun PengaturanScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // JUDUL
        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // EDIT PROFIL
        PengaturanItem(
            label = "Edit Profil",
            onClick = {
                // navController.navigate(Routes.PROFIL)
            }
        )

        // GANTI PASSWORD
        PengaturanItem(
            label = "Ganti Kata Sandi",
            onClick = {
                navController.navigate(Routes.RESET)
            }
        )

        // TENTANG APLIKASI
        PengaturanItem(
            label = "Tentang Aplikasi",
            onClick = {
                // navController.navigate(Routes.TENTANG)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // LOGOUT
        PengaturanItem(
            label = "Keluar",
            isLogout = true,
            onClick = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }
        )
    }
}

@Composable
fun PengaturanItem(
    label: String,
    isLogout: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 14.dp)
    ) {

        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = if (isLogout) FontWeight.Bold else FontWeight.Medium,
            color = if (isLogout)
                MaterialTheme.colorScheme.error
            else
                MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
    }
}
