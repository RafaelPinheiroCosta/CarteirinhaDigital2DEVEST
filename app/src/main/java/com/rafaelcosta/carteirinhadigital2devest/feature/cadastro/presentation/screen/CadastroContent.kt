package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation.CadastroUiState

@Composable
fun CadastroContent(
    uiState: CadastroUiState,
    onNomeChange: (String) -> Unit,
    onLoginChange: (String) -> Unit,
    onSenhaChange: (String) -> Unit,
    onPerfilChange: (PerfilSolicitado) -> Unit,
    onCursoChange: (String) -> Unit,
    onEnviar: () -> Unit,
    onRecarregarCursos: () -> Unit,
    onVoltar: () -> Unit
) {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Solicitar acesso", style = MaterialTheme.typography.headlineSmall)
        if (uiState.enviado) {
            Text("Cadastro recebido. Aguarde a aprovação do administrador antes de entrar.")
            Button(onClick = onVoltar) { Text("Voltar ao login") }
        } else {
            OutlinedTextField(uiState.nome, onNomeChange, label = { Text("Nome completo") },
                modifier = Modifier.fillMaxWidth())
            OutlinedTextField(uiState.login, onLoginChange, label = { Text("Login") },
                modifier = Modifier.fillMaxWidth())
            OutlinedTextField(uiState.senha, onSenhaChange, label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            Text("Quero me cadastrar como")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                PerfilSolicitado.entries.forEach { perfil ->
                    FilterChip(selected = uiState.perfil == perfil,
                        onClick = { onPerfilChange(perfil) },
                        label = { Text(if (perfil == PerfilSolicitado.ALUNO) "Aluno" else "Professor") })
                }
            }
            if (uiState.perfil == PerfilSolicitado.ALUNO) {
                Text("Curso desejado")
                uiState.cursos.forEach { curso ->
                    Row {
                        RadioButton(selected = uiState.curso == curso, onClick = { onCursoChange(curso) })
                        Text(curso, Modifier.padding(top = 12.dp))
                    }
                }
                if (uiState.cursos.isEmpty()) {
                    Text("Não foi possível listar cursos.")
                    TextButton(onClick = onRecarregarCursos) { Text("Tentar novamente") }
                }
            }
            uiState.errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            Button(
                enabled = !uiState.isLoading && (uiState.perfil != PerfilSolicitado.ALUNO ||
                    uiState.curso in uiState.cursos),
                onClick = onEnviar, modifier = Modifier.fillMaxWidth()
            ) { Text(if (uiState.isLoading) "Enviando..." else "Enviar solicitação") }
            TextButton(onClick = onVoltar) { Text("Voltar ao login") }
        }
    }
}
