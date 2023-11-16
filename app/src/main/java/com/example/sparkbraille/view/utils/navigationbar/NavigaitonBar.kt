package com.example.sparkbraille.view.utils.navigationbar

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.sparkbraille.view.utils.NavigationItems
import com.example.sparkbraille.ui.theme.WhiteBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NavigationBar(
    state: ScaffoldState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
) {
    val screens = listOf(
        NavigationItems.Home,
        NavigationItems.Profile,
        NavigationItems.Settings,
        NavigationItems.Review,
        NavigationItems.MiscPage,
        NavigationItems.PostPage,
        NavigationItems.GraphQLPage,
        NavigationItems.WeatherPage,
        NavigationItems.RatingPage
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    val scope = rememberCoroutineScope()
    var userData:List<String> = arrayListOf("null","Atanu Kumar Dey","atanukumar251@gmail.com","null","null","https://firebasestorage.googleapis.com/v0/b/regionlang.appspot.com/o/Images%2Fatanu?alt=media&token=2d032ffa-f63b-4975-89a2-e0002af9851b");


    var userName by remember  { mutableStateOf("User Name") }
    var userEmail by remember { mutableStateOf("User Email") }
    var imageURL by remember { mutableStateOf("https://www.pngmart.com/files/21/Account-User-PNG-Clipart.png")}

    val mContext = LocalContext.current

    var getDataFromStorage by remember {
        mutableStateOf(true)
    }


    if (bottomBarDestination) {

        LazyColumn(
            modifier = Modifier
                .background(color = WhiteBackground.copy(alpha = .8f))
                .fillMaxSize()
//                .padding(8.dp),
        ) {
//            item { RoundedImageHeader(
//                userName = userName,
//                userEmail = userEmail,
//                imageUrl = imageURL
//            ) }
//            item { Spacer(
//                modifier = Modifier
//                    .padding(top = 8.dp, bottom = 8.dp)
//            ) }
            item {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .height(.5.dp),
                    thickness = 10.dp,
                    color = MaterialTheme.colors.primary
                )
            }
            screens.forEach { screen ->
                item {
                    DrawerItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                            coroutineScope.launch { state.drawerState.close() }
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun DrawerItem(
    screen: NavigationItems,
    currentDestination: NavDestination?,
    onClick: () -> Unit
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true
    val background =
        if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.3f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Icon(
                imageVector = screen.icon,
                contentDescription = null,
                modifier = Modifier.padding(8.dp),
                tint = contentColor,
            )
            Text(
                text = screen.title,
                style = TextStyle(color = contentColor, fontSize = 17.sp),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


//@Preview
//@Composable
//fun NavigationBarPreview(){
//    val navController = rememberNavController()
//    val state  = rememberScaffoldState()
//    val coroutineScope = rememberCoroutineScope()
//
//    BengaliAppTheme {
//        Scaffold(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Color.White)
//        ) {
//            NavigationBar(state, coroutineScope, navController)
//        }
//    }
//}

