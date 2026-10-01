package com.rafaelcosta.carteirinhadigital2devest.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.rafaelcosta.carteirinhadigital2devest.app.di.AppContainer
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.AppNavHost
import com.rafaelcosta.carteirinhadigital2devest.core.designsystem.theme.CarteirinhaDigital2DEVESTTheme

@Composable
fun App(container: AppContainer) {
    CarteirinhaDigital2DEVESTTheme() {
        val navController = rememberNavController()
        AppNavHost(
            navController = navController,
            container=container
        )
    }
}