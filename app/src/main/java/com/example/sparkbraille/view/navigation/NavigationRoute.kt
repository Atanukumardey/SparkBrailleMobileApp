package com.example.sparkbraille.view.navigation


sealed class NavigationRoute(
    val route: String,
) {
    object SplashSceen: NavigationRoute(
        route = "SplashScreen"
    )

    object LoginScreen: NavigationRoute(
        route = "loginScreen"
    )

    object MainScreen: NavigationRoute(
        route = "mainScreen"
    )

    object HomeScreen : NavigationRoute(
        route = "homeScreen"
    )

    object ProfileScreen : NavigationRoute(
        route = "profileScreen"
    )

    object SettingsScreen : NavigationRoute(
        route = "settingsScreen"
    )

    object ReviewScreen: NavigationRoute(
        route = "reviewScreen"
    )

    object SignUpScreen: NavigationRoute(
        route = "SignUpScreen"
    )
    object ForgotPasswordScreen: NavigationRoute(
        route = "ForgotPasswordScreen"
    )
    object MiscPageScreen: NavigationRoute(
        route = "MiscPageScreen"
    )
    object  PostPageScreen: NavigationRoute(
        route = "PostPageScreen"
    )

    object GraphQLPageScreen: NavigationRoute(
        route = "GraphQLPageScreen"
    )

    object WeatherPageScreen: NavigationRoute(
        route = "WeatherPageScreen"
    )

    object RatingPageScreen: NavigationRoute(
        route = "RatingPageScreen"
    )

}
