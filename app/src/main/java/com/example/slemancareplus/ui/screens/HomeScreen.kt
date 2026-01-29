package com.example.slemancareplus.ui.screens

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.slemancareplus.navigation.Routes

/* =======================
   HOME SCREEN
   ======================= */

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        /* ===== HEADER ===== */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Beranda",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { navController.navigate(Routes.PROFIL) }
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profil"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        /* ===== WELCOME ===== */
        Text(
            text = "Selamat Datang",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Pantau, kelola, dan laporkan bantuan sosial dengan mudah melalui SlemanCare Plus.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        /* ===== MENU GRID ===== */
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            MenuGrid(navController)
        }

        Spacer(modifier = Modifier.height(24.dp))

        /* ===== INFO CARD ===== */
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Campaign,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Laporkan apabila terdapat penerima bantuan yang tidak sesuai dengan ketentuan.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

/* =======================
   MENU GRID
   ======================= */

@Composable
fun MenuGrid(navController: NavHostController) {

    val menuList = listOf(
        MenuData("Cari Data", Icons.Default.Search, Routes.CARI_DATA),
        MenuData("Pengajuan", Icons.Default.Upload, Routes.PENGAJUAN),
        MenuData("Pengaduan", Icons.Default.Report, Routes.PENGADUAN),
        MenuData("Riwayat", Icons.Default.History, Routes.RIWAYAT),
        MenuData("Agen\nTerdekat", Icons.Default.LocationOn, Routes.AGEN),
        MenuData("Informasi", Icons.Default.Info, Routes.INFORMASI),
        MenuData("Pemberdayaan", Icons.Default.Groups, Routes.PEMBERDAYAAN),
        MenuData("Aksesibilitas", Icons.Default.Accessibility, Routes.AKSESIBILITAS),
        MenuData("Pusat\nBantuan", Icons.AutoMirrored.Filled.MenuBook, Routes.PUSAT_BANTUAN)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(menuList) { menu ->
            MenuItem(
                title = menu.title,
                icon = menu.icon,
                onClick = { navController.navigate(menu.route) }
            )
        }
    }
}

/* =======================
   MENU ITEM
   ======================= */

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

/* =======================
   DATA
   ======================= */

data class MenuData(
    val title: String,
    val icon: ImageVector,
    val route: String
)
