package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes

@Composable
fun ResetPasswordScreen(navController: NavController) {

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showSuccess by remember { mutableStateOf(false) }

    var newPassVisible by remember { mutableStateOf(false) }
    var confirmPassVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        // JUDUL
        Text(
            text = "Lupa Kata Sandi",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Buat kata sandi baru untuk akun Anda",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        // PASSWORD BARU
        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Kata Sandi Baru") },
            singleLine = true,
            visualTransformation =
            if (newPassVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { newPassVisible = !newPassVisible }) {
                    Icon(
                        imageVector =
                        if (newPassVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // KONFIRMASI PASSWORD
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errorMessage = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Konfirmasi Kata Sandi") },
            singleLine = true,
            visualTransformation =
            if (confirmPassVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { confirmPassVisible = !confirmPassVisible }) {
                    Icon(
                        imageVector =
                        if (confirmPassVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // TOMBOL SIMPAN
        Button(
            onClick = {
                when {
                    newPassword.isEmpty() || confirmPassword.isEmpty() ->
                        errorMessage = "Kata sandi tidak boleh kosong"
                    newPassword.length < 8 ->
                        errorMessage = "Kata sandi minimal 8 karakter"
                    newPassword != confirmPassword ->
                        errorMessage = "Konfirmasi kata sandi tidak sama"
                    else -> showSuccess = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "SIMPAN",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

    // DIALOG BERHASIL
    if (showSuccess) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccess = false
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    }
                ) {
                    Text(
                        text = "OK",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            title = {
                Text(
                    text = "Berhasil",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text("Kata sandi berhasil diperbarui. Silakan login kembali.")
            }
        )
    }
}
