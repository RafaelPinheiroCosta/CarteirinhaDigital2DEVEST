package com.rafaelcosta.carteirinhadigital2devest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val carteirinhaApplication = application as CarteirinhaApplication
        setContent {
            App(
                container = carteirinhaApplication.container
            )
        }
    }
}