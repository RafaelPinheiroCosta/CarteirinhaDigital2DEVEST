package com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.repository.GestaoSolicitacoesRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.GestaoSolicitacoesViewModel

class GestaoSolicitacoesViewModelFactory(
    private val repository: GestaoSolicitacoesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GestaoSolicitacoesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GestaoSolicitacoesViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
