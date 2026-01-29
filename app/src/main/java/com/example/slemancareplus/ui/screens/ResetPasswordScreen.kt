package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes
import com.example.slemancareplus.ui.viewmodel.ResetPasswordViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    username: String,
    viewModel: ResetPasswordViewModel = viewModel()
) {

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    val isSuccess by viewModel.isSuccess.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsState()

    // ✅ Jika reset sukses → kembali ke login
    LaunchedEffect(isSuccess) {
        if (isSuccess) {
            navController.navigate(Routes.LOGIN) {
                popUpTo(0)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Reset Password",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                localError = null
            },
            label = { Text("Password Baru") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                localError = null
            },
            label = { Text("Konfirmasi Password") },
            modifier = Modifier.fillMaxWidth()
        )

        if (localError != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = localError!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        if (message != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            onClick = {
                when {
                    password.isBlank() || confirmPassword.isBlank() ->
                        localError = "Password tidak boleh kosong"

                    password.length < 6 ->
                        localError = "Password minimal 6 karakter"

                    password != confirmPassword ->
                        localError = "Password tidak sama"

                    else ->
                        viewModel.resetPassword(username, password)
                }
            }
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("SIMPAN")
            }
        }
    }
}
