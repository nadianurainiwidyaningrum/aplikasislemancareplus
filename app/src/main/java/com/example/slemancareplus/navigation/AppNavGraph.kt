package com.example.slemancareplus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.slemancareplus.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        /* SPLASH */
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        /* AUTH */
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.OTP) {
            OtpScreen(navController)
        }

        composable(Routes.RESET) {
            ResetPasswordScreen(navController)
        }

        /* HOME */
        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        /*  MENU  */
        composable(Routes.CARI_DATA) {
            CariDataScreen(navController)
        }

        composable(Routes.PENGAJUAN) {
            PengajuanScreen(navController)
        }

        composable(Routes.PENGADUAN) {
            PengaduanScreen(navController)
        }

        composable(Routes.AGEN_SEKITAR) {
            AgenScreen(navController)
        }

        composable(Routes.RIWAYAT) {
            RiwayatScreen(navController)
        }

        composable(Routes.PENGATURAN) {
            PengaturanScreen(navController)
        }

        composable(Routes.PANDUAN) {
            PanduanScreen(navController)
        }

        composable(Routes.PROFIL) {
            ProfilScreen(navController)
        }

        /* THANK YOU */
        composable(
            route = Routes.THANKYOU + "/{type}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val type = backStackEntry.arguments?.getString("type") ?: "pengajuan"

            val message = when (type) {
                "pengaduan" -> "Pengaduan Anda Berhasil Dikirim!"
                "pengajuan" -> "Pengajuan Bantuan Berhasil Dikirim!"
                else -> "Permintaan Berhasil Diproses!"
            }

            ThankYouScreen(
                navController = navController,
                message = message
            )
        }
    }
}
