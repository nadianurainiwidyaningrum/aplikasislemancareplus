package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes
import com.example.slemancareplus.ui.viewmodel.OtpViewModel

@Composable
fun OtpScreen(
    navController: NavController,
    username: String,
    viewModel: OtpViewModel = viewModel()
) {

    val otp = remember { mutableStateListOf("", "", "", "", "") }

    val isLoading = viewModel.isLoading
    val message = viewModel.message
    val success = viewModel.isSuccess

    // ✅ Jika OTP valid → ke reset password
    LaunchedEffect(success) {
        if (success) {
            navController.navigate(Routes.RESET + "/$username") {
                popUpTo(Routes.OTP + "/{username}") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Verifikasi OTP",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            otp.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        if (it.length <= 1 && it.all { c -> c.isDigit() }) {
                            otp[index] = it
                        }
                    },
                    modifier = Modifier.width(48.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        if (message != null) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = message,
                color = if (success)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            onClick = {
                val inputOtp = otp.joinToString("")
                if (inputOtp.length == 5) {
                    viewModel.verifyOtp(username, inputOtp)
                }
            }
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text("VERIFIKASI")
            }
        }
    }
}
