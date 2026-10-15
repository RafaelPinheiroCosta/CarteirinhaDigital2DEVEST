package com.rafaelcosta.carteirinhadigital2devest.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.PerfilAcesso
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun ProfileGuard(
    usuarioFlow: StateFlow<UsuarioLogado?>,
    perfilEsperado: PerfilAcesso,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit,
    content: @Composable (UsuarioLogado) -> Unit
) {
    val usuario by usuarioFlow.collectAsStateWithLifecycle()
    val usuarioAtual = usuario

    if (usuarioAtual == null) {
        LaunchedEffect(Unit) {
            onSessaoAusente()
        }
        return
    }

    if (usuarioAtual.perfil != perfilEsperado) {
        LaunchedEffect(usuarioAtual.perfil) {
            onPerfilInvalido(usuarioAtual.perfil)
        }
        return
    }
    content(usuarioAtual)
}