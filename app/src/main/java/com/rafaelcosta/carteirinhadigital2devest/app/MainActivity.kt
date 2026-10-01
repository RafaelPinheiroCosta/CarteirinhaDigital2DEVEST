package com.rafaelcosta.carteirinhadigital2devest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.component.PerfilAluno
import com.rafaelcosta.carteirinhadigital2devest.R
import com.rafaelcosta.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme
import com.rafaelcosta.carteirinhadigital2devest.feature.carteirinha.presetantion.screen.CarteirinhaScreen
import com.rafaelcosta.myapplication.QrCode

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
