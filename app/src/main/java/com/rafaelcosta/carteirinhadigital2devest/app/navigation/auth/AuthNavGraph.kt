package com.rafaelcosta.carteirinhadigital2devest.app.navigation.auth

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.rafaelcosta.carteirinhadigital2devest.app.navigation.GraphRoutes
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.LoginViewModel
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.factory.LoginViewModelFactory
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.screen.LoginScreen

fun NavGraphBuilder.authNavGraph(
    loginRepository: LoginRepository,
    onLoginSucesso: (UsuarioLogado) -> Unit
) {
    navigation(
        startDestination = AuthRoutes.LOGIN,
        route = GraphRoutes.AUTH
    ) {
        composable(route = AuthRoutes.LOGIN) {
            val factory = remember(loginRepository) {
                LoginViewModelFactory(
                    repository = loginRepository
                )
            }

            val loginViewModel: LoginViewModel = viewModel(
                factory = factory
            )

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSucesso = onLoginSucesso
            )
        }
    }
}