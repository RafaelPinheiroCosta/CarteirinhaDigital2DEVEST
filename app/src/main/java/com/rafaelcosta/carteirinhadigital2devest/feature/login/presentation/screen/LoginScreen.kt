package com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.LoginEvent
import com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSucesso: (UsuarioLogado) -> Unit,
    viewModel: LoginViewModel
) {
    val uiState by
        viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(
        uiState.usuarioLogado
    ) {
        uiState.usuarioLogado?.let { usuario ->

            viewModel.onEvent(
                LoginEvent.OnNavegacaoRealizada
            )

            onLoginSucesso(usuario)
        }
    }

    LoginContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        modifier = modifier.fillMaxSize()
    )
}