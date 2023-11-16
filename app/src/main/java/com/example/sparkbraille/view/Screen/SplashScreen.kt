package com.example.sparkbraille.view.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sparkbraille.view.navigation.NavigationRoute
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplashScreen(
    navController: NavHostController

) {
    var startAnimation by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    var getDataFromStorage by remember {
        mutableStateOf(true)
    }


    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = "SparkBraille"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navController.popBackStack()
        navController.navigate(NavigationRoute.MainScreen.route)

//        if (localStoreUserItemsState != null && getDataFromStorage) {
//            userDataToLocal = localStoreUserItemsState as? UserModel.UserItems
//            //Log.d("userData", userDataToLocal!!.toString())
//            if(userDataToLocal!!.userName !="null"){
//                navController.popBackStack()
//                navController.navigate(NavigationRoute.MainScreen.route)
//            }else{
//                navController.popBackStack()
//                navController.navigate(NavigationRoute.LoginScreen.route)
//            }
//            getDataFromStorage = false;
//        }
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else MaterialTheme.colors.primary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.TouchApp,
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}

//@Composable
//@Preview
//fun SplashScreenPreview() {
//    Splash(alpha = 1f)
//}
