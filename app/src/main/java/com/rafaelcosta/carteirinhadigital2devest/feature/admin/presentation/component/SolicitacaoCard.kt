package com.rafaelcosta.carteirinhadigital2devest.feature.admin.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.SolicitacaoPendente

@Composable
fun SolicitacaoCard(solicitacao: SolicitacaoPendente, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(solicitacao.nome, style = MaterialTheme.typography.titleMedium)
            Text(solicitacao.login)
            if (solicitacao.cursoSolicitado.isNotBlank()) {
                Text("Curso solicitado: ${solicitacao.cursoSolicitado}")
            }
        }
    }
}
