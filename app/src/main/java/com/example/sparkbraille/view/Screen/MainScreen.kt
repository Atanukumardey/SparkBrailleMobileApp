package com.example.sparkbraille.view.Screen

import android.content.ContentResolver
import android.content.res.AssetManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sparkbraille.view.navigation.NavigationAndBottomBarNavGraph
import com.example.sparkbraille.view.utils.bottombar.BottomBar
import com.example.sparkbraille.view.utils.navigationbar.NavigationBar
import com.example.sparkbraille.ui.theme.WhiteBackground
import com.example.sparkbraille.view.utils.TopBar


@Composable
fun MainScreen(
    rootNavController: NavHostController? = null,
    mng: AssetManager,
    contentresolver: ContentResolver,
    activitySwap: () -> Unit = {},
    activityContext:ComponentActivity? = null) {
    val navController = rememberNavController()
    val state = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
//    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
//    val selectedItem = remember { mutableStateOf(items[0]) }
Surface() {
    Scaffold(
        scaffoldState = state,
        topBar = { TopBar(rootNavController!!, coroutineScope, state) },
        bottomBar = {
            BottomBar(navController = navController)
        },
        drawerContent = { NavigationBar(state, coroutineScope, navController) },
        drawerBackgroundColor = WhiteBackground.copy(alpha = .7f),
        drawerElevation = 10.dp,
        drawerShape = RoundedCornerShape(30.dp),
        content =  {
            NavigationAndBottomBarNavGraph(
                modifier = Modifier.padding(it),
                navController = navController,
                miscActivity = activitySwap,
                contentresolver = contentresolver,
                assetManager = mng,
                activityContext = activityContext
                )
        }
    )
}

}












