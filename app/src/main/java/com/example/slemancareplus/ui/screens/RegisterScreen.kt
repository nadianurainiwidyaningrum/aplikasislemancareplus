package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes

@Composable
fun RegisterScreen(navController: NavController) {

    var nik by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        // JUDUL
        Text(
            text = "Daftar Akun",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputField("NIK", nik) { nik = it; error = null }
        InputField("Nama Lengkap", name) { name = it; error = null }
        InputField("Nomor HP", phone) { phone = it; error = null }
        InputField("Password", password, true) { password = it; error = null }
        InputField("Konfirmasi Password", confirm, true) { confirm = it; error = null }

        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // TOMBOL DAFTAR
        Button(
            onClick = {
                when {
                    nik.length != 16 -> error = "NIK harus 16 digit"
                    name.isEmpty() -> error = "Nama tidak boleh kosong"
                    phone.length < 10 -> error = "Nomor HP tidak valid"
                    password.length < 8 -> error = "Password minimal 8 karakter"
                    password != confirm -> error = "Password tidak sama"
                    else -> navController.navigate(Routes.OTP)
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
                text = "DAFTAR",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    isPassword: Boolean = false,
    onChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = true,
        visualTransformation =
        if (isPassword && !passwordVisible)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        trailingIcon =
        if (isPassword) {
            {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector =
                        if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        } else null
    )

    Spacer(modifier = Modifier.height(12.dp))
}
