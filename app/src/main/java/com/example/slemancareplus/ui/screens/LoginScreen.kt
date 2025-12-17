package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slemancareplus.R
import com.example.slemancareplus.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.gambar_whatsapp_2025_05_29_pukul_15_53_59_238b783b),
            contentDescription = "Logo SlemanCare+",
            modifier = Modifier.width(200.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Selamat Datang",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Silakan masuk untuk melanjutkan",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // NOMOR TELEPON
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Nomor Telepon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // PASSWORD
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Password") },
            singleLine = true,
            visualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector =
                        if (passwordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // BUTTON MASUK
        Button(
            onClick = {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "MASUK",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // LUPA PASSWORD
        Row {
            Text(
                text = "Lupa password? ",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = "Klik di sini",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.RESET)
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // REGISTER
        Row {
            Text(
                text = "Belum punya akun? ",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = "Daftar",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // FOOTER
        Text(
            text = "Didukung oleh",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.kabupaten_sleman_logo_vector_scaled),
            contentDescription = "Logo Sleman",
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}
