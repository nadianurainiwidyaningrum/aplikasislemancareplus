package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.slemancareplus.R
import com.example.slemancareplus.navigation.Routes
import com.example.slemancareplus.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isLoading = viewModel.isLoading
    val error = viewModel.error
    val success = viewModel.success

    /* ===== NAVIGASI SETELAH LOGIN ===== */
    LaunchedEffect(success) {
        if (success) {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Image(
            painter = painterResource(id = R.drawable.gambar_whatsapp_2025_05_29_pukul_15_53_59_238b783b),
            contentDescription = "Logo SlemanCare Plus",
            modifier = Modifier.width(180.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Masuk ke SlemanCare Plus",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Pantau dan kelola bantuan sosial Anda",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        /* ===== PHONE ===== */
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                viewModel.error = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nomor Telepon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            isError = error != null && phone.length < 10
        )

        Spacer(modifier = Modifier.height(16.dp))

        /* ===== PASSWORD ===== */
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                viewModel.error = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Kata Sandi") },
            singleLine = true,
            isError = error != null && password.isBlank(),
            visualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector =
                        if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )

        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        /* ===== BUTTON LOGIN ===== */
        Button(
            onClick = {
                when {
                    phone.length < 10 ->
                        viewModel.error = "Nomor HP tidak valid"
                    password.isBlank() ->
                        viewModel.error = "Password wajib diisi"
                    else -> {
                        viewModel.login(phone, password)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("MASUK", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        /* ===== RESET PASSWORD ===== */
        Text(
            text = "Lupa kata sandi?",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(4.dp)
                .clickable {
                    navController.navigate(Routes.RESET)
                }
        )

        Spacer(modifier = Modifier.height(24.dp))

        /* ===== REGISTER ===== */
        Row {
            Text(
                text = "Belum punya akun? ",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Daftar",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Didukung oleh Pemerintah Kabupaten Sleman",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.kabupaten_sleman_logo_vector_scaled),
            contentDescription = "Logo Sleman",
            modifier = Modifier.size(56.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
