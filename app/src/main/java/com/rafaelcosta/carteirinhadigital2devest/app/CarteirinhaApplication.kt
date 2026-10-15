package com.rafaelcosta.carteirinhadigital2devest.app

import android.app.Application
import com.rafaelcosta.carteirinhadigital2devest.app.di.AppContainer
import com.rafaelcosta.carteirinhadigital2devest.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}