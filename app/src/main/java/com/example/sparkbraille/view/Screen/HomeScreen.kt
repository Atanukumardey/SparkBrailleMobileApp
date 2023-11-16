package com.example.sparkbraille.view.Screen

import android.Manifest
import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sparkbraille.Communication.MeshCommunicator
import com.example.sparkbraille.Communication.MeshHandler
import com.example.sparkbraille.Contract.SpeechRecognizerContract
import com.example.sparkbraille.R
import com.example.sparkbraille.Translation.Translation
import com.example.sparkbraille.ui.theme.LightGreen1
import com.example.sparkbraille.ui.theme.LightGreen2
import com.example.sparkbraille.viewmodel.AudioToTextViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: AudioToTextViewModel = viewModel(),
    brailleTranslator: Translation = Translation(),
) {
    val context = LocalContext.current
    val wifiMgr = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wifiInfo = wifiMgr.connectionInfo
    if (wifiMgr != null) {
        // Create the mesh AP node ID from the AP MAC address
        MeshHandler.apNodeId = MeshHandler.createMeshID(wifiInfo.bssid)
        val dhcpInfo = wifiMgr.dhcpInfo
        // Get the mesh AP IP
        var meshIPasNumber: Int = dhcpInfo.gateway
        MeshHandler.meshIP = (meshIPasNumber and 0xFF).toString() + "." +
                (8.let { meshIPasNumber = meshIPasNumber ushr it; meshIPasNumber } and 0xFF) + "." +
                (8.let {
                    meshIPasNumber = meshIPasNumber ushr it; meshIPasNumber
                } and 0xFF) + "." + (meshIPasNumber ushr 8 and 0xFF)

        // Create our node ID
        MeshHandler.myNodeId = 2135792792;
            //MeshHandler.createMeshID(MeshHandler.getWifiMACAddress())

        // Connected to the Mesh network, start network task now
        MeshCommunicator.Connect(
            MeshHandler.meshIP,
            MeshHandler.meshPort,
            LocalContext.current
        )

    }
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract()
    ) { audioText ->
        var data = ""
        val size = audioText?.size
        if ((size != null) && (size > 0)) {
            data = audioText[0]
            viewModel.changeTextValue(data)
            val byteArray = data.toByteArray(Charsets.UTF_8)
            if (byteArray.isNotEmpty()) {
                val inbuf = IntArray(byteArray.size)
                val outbuf = IntArray(byteArray.size * 2)
                val insize = (byteArray.size - 1)
                val outsize = ((byteArray.size * 2) - 1)
                for (count in byteArray.indices) {
                    inbuf[count] = byteArray[count].toInt()
                }
                val returnSize: IntArray =
                    brailleTranslator.TranslateString(inbuf, outbuf, insize, outsize, true)
                if (returnSize[0] > 0) {
                    val stringBuilder = StringBuilder()
                    var i = 0;
                    var j = 0;
                    var cnt = 0
                    while (i < returnSize[0]) {
                        if (cnt == 0) {
                            stringBuilder.clear();
                            stringBuilder.append(127.toChar());
                            cnt++;
                        }
                        stringBuilder.append(outbuf[i++].toChar())
                        cnt++;
                        if (cnt == 3) {
                            // break condition
                            // send data
                            MeshHandler.sendNodeMessage(0, stringBuilder.toString())
                            Thread.sleep(1500);
                            cnt = 0;
                        }
                    }
                    if (cnt < 3) {
                        while (cnt < 3) {
                            stringBuilder.append(0.toChar())
                            cnt++;
                        }
                        MeshHandler.sendNodeMessage(0, stringBuilder.toString())
                        Thread.sleep(1500);
                        cnt = 0;
                        stringBuilder.clear();
                        stringBuilder.append(127.toChar())
                    }
                }
            }
        }
    }

    val  customDataSend = {
        val data = "one"
        val byteArray = data.toByteArray(Charsets.UTF_8)
        if (byteArray.isNotEmpty()) {
            val inbuf = IntArray(byteArray.size)
            val outbuf = IntArray(byteArray.size * 2)
            val insize = (byteArray.size - 1)
            val outsize = ((byteArray.size * 2) - 1)
            for (count in byteArray.indices) {
                inbuf[count] = byteArray[count].toInt()
            }
            val returnSize: IntArray =
                brailleTranslator.TranslateString(inbuf, outbuf, insize, outsize, true)
            if (returnSize[0] > 0) {
                val stringBuilder = StringBuilder()
                var i = 0;
                var j = 0;
                var cnt = 0
                while (i < returnSize[0]) {
                    if (cnt == 0) {
                        stringBuilder.clear();
                        stringBuilder.append(127.toChar());
                        cnt++;
                    }
                    stringBuilder.append(outbuf[i++].toChar())
                    cnt++;
                    if (cnt == 3) {
                        // break condition
                        // send data
                        MeshHandler.sendNodeMessage(0, stringBuilder.toString())
                        Thread.sleep(1000);
                        cnt = 0;
                    }
                }
                if (cnt < 3) {
                    while (cnt < 3) {
                        stringBuilder.append(0.toChar())
                        cnt++;

                    }
                    MeshHandler.sendNodeMessage(0, stringBuilder.toString())
                    Thread.sleep(1000);
                    cnt = 0;
                    stringBuilder.clear();
                    stringBuilder.append(127.toChar())
                }
            }
        }
    }


    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (viewModel.state.text != null) {
            Text(
                text = viewModel.state.text!!,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(45.dp))

        OutlinedButton(
            onClick = {
                if (permissionState.status.isGranted) {
//                    speechRecognizerLauncher.launch(Unit)
                    customDataSend();
                } else{
                    permissionState.launchPermissionRequest()
                }
                      },
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            border = BorderStroke(2.dp, LightGreen2),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
        ) {
            Icon(
                painterResource(id = R.drawable.record_mic),
                contentDescription = "content description",
                tint = LightGreen1.copy(alpha = .5f),
                modifier = Modifier
                    .size(100.dp)
            )
//            Text(text = "Speak")
        }

    }

}