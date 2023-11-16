package com.example.sparkbraille.view.utils


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sparkbraille.view.navigation.NavigationRoute


sealed class NavigationItems
    (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Profile : NavigationItems(
        route = NavigationRoute.ProfileScreen.route,
        title = "Profile",
        icon = Icons.Outlined.Person
    )

    object Home : NavigationItems(
        route = NavigationRoute.HomeScreen.route,
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Settings : NavigationItems(
        route = NavigationRoute.SettingsScreen.route,
        title = "Settings",
        icon = Icons.Outlined.Settings
    )

    object Review: NavigationItems(
        route = NavigationRoute.ReviewScreen.route,
        title = "Review",
        icon = Icons.Outlined.Reviews
    )

    object MiscPage: NavigationItems(
        route = NavigationRoute.MiscPageScreen.route,
        title = "Misc",
        icon = Icons.Outlined.Map
    )
    object PostPage: NavigationItems(
        route = NavigationRoute.PostPageScreen.route,
        title = "Social",
        icon = Icons.Outlined.MeetingRoom
    )

    object GraphQLPage: NavigationItems(
        route = NavigationRoute.GraphQLPageScreen.route,
        title = "GqaphQL",
        icon = Icons.Outlined.Analytics
    )

    object WeatherPage: NavigationItems(
        route = NavigationRoute.WeatherPageScreen.route,
        title = "RestAPI",
        icon = Icons.Outlined.Cloud
    )

    object RatingPage: NavigationItems(
        route = NavigationRoute.RatingPageScreen.route,
        title = "App Rating",
        icon = Icons.Outlined.RateReview
    )

}
