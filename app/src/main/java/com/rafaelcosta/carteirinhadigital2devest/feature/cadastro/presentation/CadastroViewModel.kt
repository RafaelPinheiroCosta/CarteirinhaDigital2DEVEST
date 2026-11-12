package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.model.SolicitacaoCadastro
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.repository.CadastroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CadastroViewModel(private val repository: CadastroRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(CadastroUiState())
    val uiState = _uiState.asStateFlow()

    init { carregarCursos() }

    fun alterarNome(value: String) = _uiState.update { it.copy(nome = value, errorMessage = null) }
    fun alterarLogin(value: String) = _uiState.update { it.copy(login = value, errorMessage = null) }
    fun alterarSenha(value: String) = _uiState.update { it.copy(senha = value, errorMessage = null) }
    fun selecionarPerfil(value: PerfilSolicitado) = _uiState.update {
        it.copy(perfil = value, curso = "", errorMessage = null)
    }
    fun selecionarCurso(value: String) = _uiState.update { it.copy(curso = value, errorMessage = null) }

    fun carregarCursos() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listarCursos().onSuccess { cursos ->
                _uiState.update { it.copy(cursos = cursos, isLoading = false) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }

    fun cadastrar() {
        val state = _uiState.value
        if (state.isLoading || state.enviado) return
        if (state.nome.isBlank() || state.login.isBlank() || state.senha.length < 8 ||
            (state.perfil == PerfilSolicitado.ALUNO && state.curso !in state.cursos)) {
            _uiState.update {
                it.copy(errorMessage = "Preencha nome, login, senha (mínimo 8 caracteres) e curso válido para aluno.")
            }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.solicitarCadastro(
                SolicitacaoCadastro(
                    nome = state.nome.trim(),
                    login = state.login.trim(),
                    senha = state.senha,
                    perfil = state.perfil,
                    curso = if (state.perfil == PerfilSolicitado.ALUNO) state.curso else null
                )
            ).onSuccess {
                _uiState.update { it.copy(isLoading = false, enviado = true, senha = "") }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }
}
