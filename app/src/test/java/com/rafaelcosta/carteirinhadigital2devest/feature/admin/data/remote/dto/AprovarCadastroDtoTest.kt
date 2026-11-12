package com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.dto

import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.*
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test

class AprovarCadastroDtoTest {
    private val json = Json { encodeDefaults = false; explicitNulls = false }

    @Test fun alunoEnviaSomenteTurmaEMatricula() {
        val dto = AprovarCadastroDto.fromDomain(AprovacaoSolicitacao.Aluno("2DEVEST-A", "20260001"))
        val body = json.encodeToString(AprovarCadastroDto.serializer(), dto)
        assertTrue(body.contains("\"turma\":\"2DEVEST-A\""))
        assertTrue(body.contains("\"matricula\":\"20260001\""))
        assertFalse(body.contains("\"perfil\""))
        assertFalse(body.contains("\"curso\""))
        assertFalse(body.contains("\"atribuicoes\""))
    }

    @Test fun professorEnviaAtribuicoesSemPerfil() {
        val dto = AprovarCadastroDto.fromDomain(AprovacaoSolicitacao.Professor(
            listOf(AtribuicaoDocente("2DEVEST-A", "Programação Back-End"))
        ))
        val body = json.encodeToString(AprovarCadastroDto.serializer(), dto)
        assertTrue(body.contains("\"atribuicoes\""))
        assertFalse(body.contains("\"perfil\""))
        assertFalse(body.contains("\"matricula\""))
    }
}
