package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slemancareplus.R
import com.example.slemancareplus.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // Delay 2 detik lalu ke LOGIN
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Routes.LOGIN) {
            popUpTo(Routes.SPLASH) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(100.dp))

            // LOGO
            Image(
                painter = painterResource(
                    id = R.drawable.gambar_whatsapp_2025_05_29_pukul_15_53_59_238b783b
                ),
                contentDescription = "Logo SlemanCare Plus",
                modifier = Modifier.width(220.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Didukung Oleh:",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(
                    id = R.drawable.kabupaten_sleman_logo_vector_scaled
                ),
                contentDescription = "Logo Sleman",
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pemerintah Kabupaten Sleman",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
