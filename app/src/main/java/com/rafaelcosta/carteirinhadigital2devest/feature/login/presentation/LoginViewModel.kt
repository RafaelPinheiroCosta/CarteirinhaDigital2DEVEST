package com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.FakeLoginRepositoryImpl
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.LoginRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.LoginRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepositoryProvider.provide()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnUsuarioChange -> {
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        erroMessage = null
                    )
                }
            }
            is LoginEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        erroMessage = null
                    )
                }
            }
            LoginEvent.OnNavegacaoRealizada -> {
                _uiState.update {
                    it.copy(
                        usuarioLogado = null
                    )
                }
            }

            LoginEvent.OnEntrarClick -> fazerLogin()
        }
    }

    private fun fazerLogin() {
        val state = _uiState.value

        if(state.usuario.isBlank() || state.senha.isBlank()){
            _uiState.update {
                it.copy(
                    erroMessage = "Preencha login e senha"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    erroMessage = null,
                    usuarioLogado = null
                )
            }
            val result = repository.login(
                state.usuario.trim(),
                state.senha.trim()
            )

            result
                .onSuccess { usuarioLogado ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            erroMessage = null,
                            usuarioLogado = usuarioLogado
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            erroMessage = throwable.message ?: "Erro ao fazer Login"
                        )
                    }
                }
        }
    }


}