package com.example.slemancareplus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.slemancareplus.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.REGISTER) {
            RegisterScreen(navController)
        }

        composable(Routes.RESET) {
            ResetPasswordScreen(navController, "")
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }

        composable(
            route = Routes.OTP + "/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) {
            val username = it.arguments?.getString("username") ?: ""
            OtpScreen(navController, username)
        }

        composable(
            route = Routes.RESET + "/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) {
            val username = it.arguments?.getString("username") ?: ""
            ResetPasswordScreen(navController, username)
        }
    }
}
