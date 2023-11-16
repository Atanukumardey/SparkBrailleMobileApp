package com.example.sparkbraille.view.navigation

import android.content.ContentResolver
import android.content.res.AssetManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sparkbraille.view.Screen.AnimatedSplashScreen
import com.example.sparkbraille.view.Screen.MainScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    mng: AssetManager,
    contentresolver: ContentResolver,
    activitySwap: () -> Unit = {},
    activityContext: ComponentActivity? = null
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SplashSceen.route
    ) {
        composable(route = NavigationRoute.SplashSceen.route){
            AnimatedSplashScreen(navController)
        }
        composable(route = NavigationRoute.LoginScreen.route){
//            LoginScreen(navController)
        }
        composable(route = NavigationRoute.MainScreen.route){
            MainScreen(navController,mng,contentresolver, activitySwap, activityContext)
        }
        composable(route = NavigationRoute.SignUpScreen.route){
//            SignUpScreen(navController)
        }
        composable(route = NavigationRoute.ForgotPasswordScreen.route){
//            ForgotPasswordScreen(navController)
        }
    }
}