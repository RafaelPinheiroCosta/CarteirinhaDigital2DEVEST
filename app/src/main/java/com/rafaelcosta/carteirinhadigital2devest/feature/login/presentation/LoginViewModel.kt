package com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.OnUsuarioChange ->{
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        errorMessage = null,
                        credentialError = false
                    )
                }
            }
            is LoginEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        errorMessage = null,
                        credentialError = false
                    )
                }
            }
            LoginEvent.OnEntrarClick -> fazerLogin()

            LoginEvent.OnNavegacaoRealizada -> {
                _uiState.update {
                    it.copy(
                        usuarioLogado = null
                    )
                }
            }
        }

    }

    private fun fazerLogin() {
        val state = _uiState.value

        if(state.usuario.isBlank() || state.senha.isBlank()){
            _uiState.update {
                it.copy(
                    errorMessage = "Preencha login e senha",
                    credentialError = true
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    credentialError = false,
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
                            errorMessage = null,
                            credentialError = false,
                            usuarioLogado = usuarioLogado
                        )
                    }
                }
                .onFailure { throwable ->
                    val errorMessage = throwable.message ?: "Erro ao fazer login."
                    val isCredentialError = errorMessage.contains("login ou senha", ignoreCase = true)
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = errorMessage,
                            credentialError = isCredentialError
                        )
                    }

                }
        }
    }
}
