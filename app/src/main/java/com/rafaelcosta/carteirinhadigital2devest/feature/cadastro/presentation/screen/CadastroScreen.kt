package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation.CadastroViewModel

@Composable
fun CadastroScreen(viewModel: CadastroViewModel, onVoltar: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CadastroContent(
        uiState = uiState,
        onNomeChange = viewModel::alterarNome,
        onLoginChange = viewModel::alterarLogin,
        onSenhaChange = viewModel::alterarSenha,
        onPerfilChange = viewModel::selecionarPerfil,
        onCursoChange = viewModel::selecionarCurso,
        onEnviar = viewModel::cadastrar,
        onRecarregarCursos = viewModel::carregarCursos,
        onVoltar = onVoltar
    )
}
