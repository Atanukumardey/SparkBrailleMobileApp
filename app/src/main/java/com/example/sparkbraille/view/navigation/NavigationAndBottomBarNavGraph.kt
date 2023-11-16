package com.example.sparkbraille.view.navigation

import android.content.ContentResolver
import android.content.res.AssetManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sparkbraille.view.Screen.HomeScreen

@Composable
fun NavigationAndBottomBarNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    miscActivity: () -> Unit = {},
    assetManager: AssetManager,
    contentresolver: ContentResolver,
    activityContext: ComponentActivity?= null
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.HomeScreen.route
    ) {
        composable(route = NavigationRoute.HomeScreen.route) {
//            HomeScreen(assetManager,contentresolver)
            HomeScreen()
        }
        composable(route = NavigationRoute.ProfileScreen.route) {
//            ProfileScreen(contentresolver,activityContext)
        }
        composable(route = NavigationRoute.SettingsScreen.route) {
//            SettingsScreen()
        }
        composable(route = NavigationRoute.ReviewScreen.route){
//            ReviewScreen()
        }
        composable(route = NavigationRoute.MiscPageScreen.route){
//            miscActivity()
//            MapsActivity()
//            MiscPage(miscActivity)
        }
        composable(route = NavigationRoute.PostPageScreen.route){
//            PostPage(contentresolver,activityContext!!)
        }
        composable(route = NavigationRoute.GraphQLPageScreen.route){
//            CountriesScreen()
        }
        composable(route = NavigationRoute.WeatherPageScreen.route){
//            WeatherScreen()
        }

        composable(route = NavigationRoute.RatingPageScreen.route){
//            RatingScreen()
        }
    }
}