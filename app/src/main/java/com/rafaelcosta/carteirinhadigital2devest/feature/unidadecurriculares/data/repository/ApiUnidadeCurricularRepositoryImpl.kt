package com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.model.UnidadeCurricular
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository
import retrofit2.HttpException
import java.io.IOException

class ApiUnidadeCurricularRepositoryImpl(
    private val api: UnidadeCurricularApi
) : UnidadeCurricularRepository {

    override suspend fun listar(): Result<List<UnidadeCurricular>> {
        return runCatching {
            api.listar().map {
                it.toDomain()
            }
        }.recoverCatching { throwable ->
            throw when (throwable) {
                is HttpException -> {
                    if (throwable.code() == 401) {
                        IllegalStateException("Sua sessão expirou. Faça login novamente.")
                    } else {
                        IllegalStateException("Erro ao carregar unidades curriculares (${throwable.code()}).")
                    }
                }
                is IOException ->
                    IllegalStateException("Não foi possível conectar à API.")
                else ->
                    IllegalStateException(throwable.message ?: "Erro ao carregar unidades curriculares.")
            }
        }
    }
}