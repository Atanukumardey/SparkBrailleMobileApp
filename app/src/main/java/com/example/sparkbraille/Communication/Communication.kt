package com.example.sparkbraille.Communication

import java.net.Socket

open class Communication {
    public fun sendTextToMeshNetwork(text: String) {
        // Your code to send the text message to the mesh network using painlessMesh
        // You can use the ESP8266's IP address and port to establish a socket connection
        // with the nodes in the mesh network and send the text message as a string.

        // For example:
         val ipAddress = "192.168.1.100"
         val port = 5555
         val socket = Socket(ipAddress, port)
         val outputStream = socket.getOutputStream()
         outputStream.write(text.toByteArray())
         outputStream.close()
         socket.close()

        // Note: In a real-world scenario, you'll need to handle socket connections and
        // error handling properly. Ensure that the ESP8266 nodes are listening for incoming
        // messages and processing them accordingly.
    }
}