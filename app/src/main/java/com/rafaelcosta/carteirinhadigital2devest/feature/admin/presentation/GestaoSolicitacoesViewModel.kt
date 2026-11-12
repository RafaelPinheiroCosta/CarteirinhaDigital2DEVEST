package com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.*
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.repository.GestaoSolicitacoesRepository
import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GestaoSolicitacoesViewModel(
    private val repository: GestaoSolicitacoesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(GestaoSolicitacoesUiState())
    val uiState = _uiState.asStateFlow()

    init { carregar() }

    fun selecionarAba(perfil: PerfilSolicitado) {
        _uiState.update { it.copy(aba = perfil, pendentes = emptyList(), selecionado = null,
            confirmarRejeicao = false, errorMessage = null, mensagem = null) }
        carregar()
    }

    fun abrirSolicitacao(id: String) {
        _uiState.update { atual ->
            atual.copy(
                selecionado = atual.pendentes.firstOrNull { it.id == id },
                turmaSelecionada = "", matricula = "", atribuicoes = emptySet(),
                confirmarRejeicao = false, errorMessage = null
            )
        }
    }

    fun voltarLista() {
        _uiState.update { it.copy(selecionado = null, confirmarRejeicao = false, errorMessage = null) }
    }

    fun selecionarTurma(codigo: String) = _uiState.update {
        it.copy(turmaSelecionada = codigo, errorMessage = null)
    }

    fun alterarMatricula(valor: String) = _uiState.update {
        it.copy(matricula = valor, errorMessage = null)
    }

    fun marcarAtribuicao(atribuicao: AtribuicaoDocente, marcada: Boolean) = _uiState.update {
        it.copy(
            atribuicoes = if (marcada) it.atribuicoes + atribuicao else it.atribuicoes - atribuicao,
            errorMessage = null
        )
    }

    fun solicitarRejeicao() = _uiState.update { it.copy(confirmarRejeicao = !it.isLoading && it.selecionado != null) }
    fun cancelarRejeicao() = _uiState.update { it.copy(confirmarRejeicao = false) }

    fun carregar() {
        val perfil = _uiState.value.aba
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val pendentes = repository.listarPendentes(perfil)
            val turmas = repository.listarTurmas()
            if (_uiState.value.aba != perfil) return@launch
            if (pendentes.isFailure || turmas.isFailure) {
                val erro = pendentes.exceptionOrNull() ?: turmas.exceptionOrNull()
                _uiState.update { it.copy(isLoading = false,
                    errorMessage = erro?.message ?: "Erro ao carregar solicitações.") }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        pendentes = pendentes.getOrThrow(),
                        turmas = turmas.getOrThrow()
                    )
                }
            }
        }
    }

    fun aprovar() {
        val state = _uiState.value
        val candidato = state.selecionado ?: return
        if (!state.podeAprovar) return
        val aprovacao = when (candidato.perfilSolicitado) {
            PerfilSolicitado.ALUNO -> AprovacaoSolicitacao.Aluno(
                turma = state.turmaSelecionada, matricula = state.matricula.trim()
            )
            PerfilSolicitado.PROFESSOR -> AprovacaoSolicitacao.Professor(
                atribuicoes = state.atribuicoes.sortedWith(compareBy({ it.turma }, { it.unidadeCurricular }))
            )
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.aprovar(candidato.id, aprovacao)
                .onSuccess {
                    _uiState.update { it.copy(
                        selecionado = null, isLoading = false,
                        mensagem = "Solicitação aprovada com sucesso."
                    ) }
                    carregar()
                }.onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }

    fun rejeitar() {
        if (_uiState.value.isLoading || !_uiState.value.confirmarRejeicao) return
        val candidato = _uiState.value.selecionado ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, confirmarRejeicao = false, errorMessage = null) }
            repository.rejeitar(candidato.id).onSuccess {
                _uiState.update { it.copy(
                    selecionado = null, isLoading = false,
                    mensagem = "Solicitação rejeitada e cadastro excluído."
                ) }
                carregar()
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }
}
