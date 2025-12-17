package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes

@Composable
fun ForgotPasswordScreen(navController: NavController) {

    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var method by remember { mutableStateOf("WA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "LUPA KATA SANDI",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pilih Metode Pengiriman OTP",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = null
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nomor Telepon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            isError = phoneError != null
        )

        if (phoneError != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = phoneError!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                MethodButton("WhatsApp", method == "WA") { method = "WA" }
            }
            Box(modifier = Modifier.weight(1f)) {
                MethodButton("SMS", method == "SMS") { method = "SMS" }
            }
            Box(modifier = Modifier.weight(1f)) {
                MethodButton("Telepon", method == "CALL") { method = "CALL" }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                when {
                    phone.isEmpty() -> phoneError = "Nomor tidak boleh kosong"
                    phone.length < 10 -> phoneError = "Nomor tidak valid"
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
                text = "KIRIM OTP",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MethodButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primary
            else
                Color.White,
            contentColor = if (selected)
                Color.White
            else
                MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
