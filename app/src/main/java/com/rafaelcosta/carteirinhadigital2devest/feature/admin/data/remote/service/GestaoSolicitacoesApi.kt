package com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.service

import com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface GestaoSolicitacoesApi {
    @GET("admin/solicitacoes")
    suspend fun listarPendentes(@Query("perfil") perfil: String): List<SolicitacaoPendenteDto>

    @GET("admin/catalogo/turmas")
    suspend fun listarTurmas(): List<TurmaCatalogoDto>

    @PUT("admin/solicitacoes/{id}/aprovacao")
    suspend fun aprovar(@Path("id") id: String, @Body dto: AprovarCadastroDto): Response<Void>

    @DELETE("admin/solicitacoes/{id}")
    suspend fun rejeitar(@Path("id") id: String): Response<Void>
}
