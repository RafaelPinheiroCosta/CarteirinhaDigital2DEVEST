package com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel

class UnidadeCurricularViewModelFactory(
    private val repository: UnidadeCurricularRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnidadeCurricularViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnidadeCurricularViewModel(repository =repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}