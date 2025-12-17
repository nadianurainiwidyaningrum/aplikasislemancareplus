package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun OtpScreen(navController: NavController) {

    val otp = remember { mutableStateListOf("", "", "", "", "") }
    var timeLeft by remember { mutableIntStateOf(60) }
    var canResend by remember { mutableStateOf(false) }
    var otpError by remember { mutableStateOf(false) }

    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else {
            canResend = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "VERIFIKASI OTP",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Masukkan kode OTP yang dikirim",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // OTP INPUT
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            otp.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        if (it.length <= 1) {
                            otp[index] = it
                            otpError = false
                        }
                    },
                    modifier = Modifier
                        .width(50.dp)
                        .height(56.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        if (otpError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Kode OTP salah",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TIMER
        Text(
            text = if (canResend)
                "Kirim ulang OTP"
            else
                "Kirim ulang dalam $timeLeft detik",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = if (canResend)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.clickable(enabled = canResend) {
                timeLeft = 60
                canResend = false
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // BUTTON VERIFIKASI
        Button(
            onClick = {
                val inputOtp = otp.joinToString("")
                when {
                    inputOtp.length < 5 -> otpError = true
                    inputOtp != "22533" -> otpError = true // OTP dummy
                    else -> navController.navigate(Routes.RESET)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "VERIFIKASI",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
