package com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.GestaoSolicitacoesViewModel

@Composable
fun GestaoSolicitacoesScreen(viewModel: GestaoSolicitacoesViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    GestaoSolicitacoesContent(
        uiState = uiState,
        modifier = modifier,
        onAbaChange = viewModel::selecionarAba,
        onSelecionarSolicitacao = viewModel::abrirSolicitacao,
        onTurmaChange = viewModel::selecionarTurma,
        onMatriculaChange = viewModel::alterarMatricula,
        onAtribuicaoChange = viewModel::marcarAtribuicao,
        onAprovar = viewModel::aprovar,
        onSolicitarRejeicao = viewModel::solicitarRejeicao,
        onCancelarRejeicao = viewModel::cancelarRejeicao,
        onRejeitar = viewModel::rejeitar,
        onVoltar = viewModel::voltarLista,
        onAtualizar = viewModel::carregar
    )
}
