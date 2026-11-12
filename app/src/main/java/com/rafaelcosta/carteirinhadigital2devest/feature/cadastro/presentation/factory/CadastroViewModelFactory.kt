package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.repository.CadastroRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation.CadastroViewModel

class CadastroViewModelFactory(private val repository: CadastroRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastroViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CadastroViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
