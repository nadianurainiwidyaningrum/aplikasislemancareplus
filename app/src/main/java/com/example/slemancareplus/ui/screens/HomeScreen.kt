package com.example.slemancareplus.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slemancareplus.navigation.Routes

@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        /* ================= HEADER ================= */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "BERANDA",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profil",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        navController.navigate(Routes.PROFIL)
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        /* ================= WELCOME ================= */
        Text(
            text = "Selamat datang!",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Pantau bantuan sosialmu di sini.\n" +
                    "Laporkan jika menemukan penerima bansos\n" +
                    "yang tidak tepat sasaran.",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        /* ================= MENU CARD ================= */
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                MenuGrid(navController)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        /* ================= LAPORAN CARD ================= */
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFBBDEFB)
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Campaign,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "LAPORKAN KEPADA KAMI\n" +
                            "JIKA MENEMUKAN OKNUM ATAU\n" +
                            "PENERIMA YANG SALAH SASARAN",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }
    }
}

/* ================= MENU GRID ================= */

@Composable
fun MenuGrid(navController: NavController) {

    val menus = listOf(
        Triple("Cari Data", Icons.Default.Search, Routes.CARI_DATA),
        Triple("Pengajuan", Icons.Default.Upload, Routes.PENGAJUAN),
        Triple("Pengaduan", Icons.Default.Report, Routes.PENGADUAN),
        Triple("Riwayat", Icons.Default.History, Routes.RIWAYAT),
        Triple("Agen Sekitar", Icons.Default.LocationOn, Routes.AGEN_SEKITAR),
        Triple("Panduan", Icons.AutoMirrored.Filled.MenuBook, Routes.PANDUAN)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(menus) { menu ->
            MenuItem(
                title = menu.first,
                icon = menu.second
            ) {
                navController.navigate(menu.third)
            }
        }
    }
}

/* ================= MENU ITEM ================= */

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp) // 🔑 SEMUA KOTAK SAMA TINGGI
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            lineHeight = 14.sp,
            color = Color.Black
        )
    }
}
