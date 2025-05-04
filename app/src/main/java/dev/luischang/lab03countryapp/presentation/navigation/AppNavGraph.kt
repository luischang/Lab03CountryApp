package dev.luischang.lab03countryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luischang.lab03countryapp.presentation.auth.LoginScreen
import dev.luischang.lab03countryapp.presentation.auth.RegisterScreen
import dev.luischang.lab03countryapp.presentation.home.HomeScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen() }
    }

}