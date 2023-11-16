package com.example.sparkbraille

import android.Manifest
import android.content.res.AssetManager
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.compose.rememberNavController
import com.example.sparkbraille.Communication.MeshCommunicator
import com.example.sparkbraille.Communication.MeshHandler
import com.example.sparkbraille.ui.theme.SparkBrailleTheme
import com.example.sparkbraille.view.navigation.RootNavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mng: AssetManager = assets
        var contentresolver = contentResolver
        var permissionLauncher: ActivityResultLauncher<Array<String>> = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {

        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
        ))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){
                view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom=bottom)
            insets
        }

        // Start handler to send node sync request and time sync request every 5 seconds
        // Keeps socket connection more stable

        // Start handler to send node sync request and time sync request every 5 seconds
        // Keeps socket connection more stable
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            var timeForNodeReq = true
            override fun run() {
                if (MeshCommunicator.isConnected()) {
                    timeForNodeReq = if (timeForNodeReq) {
                        MeshHandler.sendNodeSyncRequest()
                        false
                    } else {
                        MeshHandler.sendTimeSyncRequest()
                        true
                    }
                }
                handler.postDelayed(this, 10000)
            }
        }, 10000)
        setContent {
            SparkBrailleTheme {
                window.statusBarColor= androidx.compose.material.MaterialTheme.colors.primary.copy(alpha = 0.5f).toArgb()
                val navController = rememberNavController()
                RootNavigationGraph(
                    navController = navController,
                    mng = mng,
                    contentresolver = contentresolver,
                    activityContext = this,
                    activitySwap = {},
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SparkBrailleTheme {
//        Greeting("Android")
//    }
//}