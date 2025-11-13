package com.example.loginjetpackcompose.core.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.loginjetpackcompose.HomeScreen
import com.example.loginjetpackcompose.LoginScreen
@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login>{
            LoginScreen {navController.navigate(Home(name = it))}
        }
        composable<Home> {backStackEntry ->
            val home: Home = backStackEntry.toRoute()
            HomeScreen (name = home.name) {
                navController.navigate(Login) {
                    popUpTo<Login> { inclusive = true }
                }
            }
        }

    }
}
