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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes
import com.example.slemancareplus.ui.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {

    var nik by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }

    val error = viewModel.error
    val isLoading = viewModel.isLoading
    val success = viewModel.success

    /* ===== NAVIGASI SETELAH REGISTER ===== */
    LaunchedEffect(success) {
        if (success) {
            navController.navigate(Routes.OTP) {
                popUpTo(Routes.REGISTER) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Daftar Akun",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputField("NIK", nik, isError = error != null && nik.length != 16) {
            nik = it
            viewModel.error = null
        }

        InputField("Nama Lengkap", name, isError = error != null && name.isBlank()) {
            name = it
            viewModel.error = null
        }

        InputField("Nomor HP", phone, isError = error != null && phone.length < 10) {
            phone = it
            viewModel.error = null
        }

        InputField(
            label = "Password",
            value = password,
            isPassword = true,
            isError = error != null && password.length < 8
        ) {
            password = it
            viewModel.error = null
        }

        InputField(
            label = "Konfirmasi Password",
            value = confirm,
            isPassword = true,
            isError = error != null && confirm != password
        ) {
            confirm = it
            viewModel.error = null
        }

        if (error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                when {
                    nik.length != 16 ->
                        viewModel.error = "NIK harus 16 digit"
                    name.isBlank() ->
                        viewModel.error = "Nama wajib diisi"
                    phone.length < 10 ->
                        viewModel.error = "Nomor HP tidak valid"
                    password.length < 8 ->
                        viewModel.error = "Password minimal 8 karakter"
                    password != confirm ->
                        viewModel.error = "Password tidak sama"
                    else -> {
                        viewModel.register(
                            nik = nik,
                            nama = name,
                            phone = phone,
                            password = password
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("DAFTAR")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        /* ===== NAVIGASI KE LOGIN ===== */
        TextButton(
            onClick = {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.REGISTER) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sudah punya akun? Masuk")
        }
    }
}

/* =========================
   INPUT FIELD COMPONENT
   ========================= */

@Composable
fun InputField(
    label: String,
    value: String,
    isPassword: Boolean = false,
    isError: Boolean = false,
    onChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = true,
        isError = isError,
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
                        contentDescription = null
                    )
                }
            }
        } else null
    )

    Spacer(modifier = Modifier.height(12.dp))
}
