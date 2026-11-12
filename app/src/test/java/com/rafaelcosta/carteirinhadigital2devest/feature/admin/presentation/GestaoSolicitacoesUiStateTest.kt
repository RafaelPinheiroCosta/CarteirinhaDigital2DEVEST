package com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation

import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.*
import org.junit.Assert.*
import org.junit.Test

class GestaoSolicitacoesUiStateTest {
    @Test fun alunoSomentePermiteAprovacaoComTurmaDoCursoEMatricula() {
        val candidato = SolicitacaoPendente("1", "Aluno", "aluno", PerfilSolicitado.ALUNO, "Desenvolvimento de Sistemas")
        val turmaValida = TurmaCatalogo("2DEVEST-A", "Desenvolvimento de Sistemas", emptyList())
        val turmaOutra = TurmaCatalogo("OUTRA", "Outro curso", emptyList())
        val base = GestaoSolicitacoesUiState(selecionado = candidato, turmas = listOf(turmaValida, turmaOutra))
        assertFalse(base.podeAprovar)
        assertFalse(base.copy(turmaSelecionada = "OUTRA", matricula = "100").podeAprovar)
        assertFalse(base.copy(turmaSelecionada = "2DEVEST-A").podeAprovar)
        assertTrue(base.copy(turmaSelecionada = "2DEVEST-A", matricula = "100").podeAprovar)
        assertFalse(base.copy(turmaSelecionada = "2DEVEST-A", matricula = "100", isLoading = true).podeAprovar)
    }

    @Test fun professorExigeUmaOuMaisAtribuicoesSemTrocarPerfil() {
        val candidato = SolicitacaoPendente("2", "Docente", "prof", PerfilSolicitado.PROFESSOR, "")
        val base = GestaoSolicitacoesUiState(selecionado = candidato)
        assertFalse(base.podeAprovar)
        assertTrue(base.copy(atribuicoes = setOf(AtribuicaoDocente("2DEVEST-A", "Programação Back-End"))).podeAprovar)
    }
}
