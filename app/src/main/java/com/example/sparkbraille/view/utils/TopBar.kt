package com.example.sparkbraille.view.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    navController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    state: ScaffoldState = rememberScaffoldState(),
) {
    var expanded by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
//    val localDatastoreViewModel = LocalDataStoreViewModel (mContext)

    TopAppBar(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
        title = { Text("Spark Braille") },
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch { state.drawerState.open() }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            // RowScope here, so these icons will be placed horizontally
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Share, contentDescription = "share")
            }
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "more")
            }

//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//                offset = DpOffset(5.dp,5.dp),
//            ) {
//                DropdownMenuItem(onClick = {
//                    localDatastoreViewModel.clearLocalData()
//                    navController.navigate(NavigationRoute.LoginScreen.route) {
//                        popUpTo(navController.graph.findStartDestination().id)
//                        navController.popBackStack()
//                        launchSingleTop = true
//                    }
//                }) {
//                    Text(text = "Logout")
//                }
//                // Add more DropdownMenuItems as needed
//            }
        },
        elevation = 10.dp
    )
}
