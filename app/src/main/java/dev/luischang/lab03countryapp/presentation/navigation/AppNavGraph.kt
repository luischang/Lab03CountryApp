package dev.luischang.lab03countryapp.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luischang.lab03countryapp.presentation.auth.LoginScreen
import dev.luischang.lab03countryapp.presentation.auth.RegisterScreen
import dev.luischang.lab03countryapp.presentation.home.HomeScreen
import dev.luischang.lab03countryapp.presentation.permissions.GalleryPermissionScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register"){
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }

        composable("home") {
            DrawerScaffold(navController) {
                HomeScreen()
            }
        }

        composable("gallery") {
            DrawerScaffold(navController) {
                GalleryPermissionScreen()
            }
        }

        composable("favorites") {
            DrawerScaffold(navController) {
                //TODO: Add favorites screen
                Text("Favorites (Screen)")
            }
        }

    }

}